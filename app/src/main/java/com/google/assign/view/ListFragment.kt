package com.google.assign.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.assign.adapter.ListAdapter
import com.google.assign.api.ApiService
import com.google.assign.databinding.ListFragmentBinding
import com.google.assign.utils.ConnectivityReceiver
import com.google.assign.utils.isConnected
import com.google.assign.utils.toast
import com.google.assign.viewModel.ListViewModel
import com.google.assign.viewModel.ListViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListFragment : BaseFragment(), ConnectivityReceiver.ConnectivityReceiverListener {

    private lateinit var listViewModel: ListViewModel
    private lateinit var listAdapter: ListAdapter
    private lateinit var binding: ListFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        listViewModel =
            ViewModelProvider(this, ListViewModelFactory(ApiService.getApiService())).get(
                ListViewModel::class.java
            )

        context?.let {
            if (it.isConnected()) {
                launch {
                    it.toast("Loading please wait...")
                    setupRecyclerView()
                    setData()
                }
            } else {
                context?.toast("Internet disconnected!")
            }
        }
    }


    private fun setupRecyclerView() {
        listAdapter = ListAdapter(requireContext())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }


    private fun setData() {
        launch {
            listViewModel.listData.collect {
                listAdapter.submitData(it)
            }
        }
    }


    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (isConnected) {
            launch {
                context?.toast("Loading data...")
                setupRecyclerView()
                setData()
            }
        } else {
            context?.toast("Internet disconnected!")
        }
    }


    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }


}
