package com.google.assign.ui.list

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.google.assign.R
import com.google.assign.databinding.ListFragmentBinding
import com.google.assign.model.User
import com.google.assign.network.ApiService
import com.google.assign.ui.BaseFragment
import com.google.assign.ui.ListViewModel
import com.google.assign.viewModel.ListViewModelFactory
import com.google.assign.viewModel.SharedViewModel
import kotlinx.coroutines.launch

class ListFragment : BaseFragment(), UserInterface {

    private lateinit var viewModel: ListViewModel
    private lateinit var listAdapter: ListAdapter
    private lateinit var binding: ListFragmentBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, ListViewModelFactory(ApiService.getApiService())).get(
            ListViewModel::class.java
        )
        setupRecyclerView()
        observers()
    }


    private fun setupRecyclerView() {
        listAdapter = ListAdapter(this)
        binding.recyclerView.adapter = listAdapter

        binding.swipeRefresh.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                observers()
                binding.swipeRefresh.isRefreshing = false
            }, 2000)
        }

        listAdapter.addLoadStateListener { loadState ->
            binding.progressView.isVisible = loadState.source.refresh is LoadState.Loading
        }
    }


    private fun observers() {
        with(viewModel) {

            lifecycleScope.launch {
                usersList.observe(viewLifecycleOwner, {
                    listAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                })
            }
        }
    }


    override fun userClick(user: User) {
        sharedViewModel.user = user
        findNavController().navigate(R.id.detailFragment)
    }

}
