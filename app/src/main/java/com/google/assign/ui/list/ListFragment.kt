package com.google.assign.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.google.assign.R
import com.google.assign.base.BaseFragment
import com.google.assign.databinding.ListFragmentBinding
import com.google.assign.model.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : BaseFragment(), ListPagingDataAdapter.AdapterInterface {

    private lateinit var binding: ListFragmentBinding
    private lateinit var adapter: ListPagingDataAdapter
    private val listViewModel: ListViewModel by viewModel()

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

        if (isConnected()) {
            setupRecyclerView()
            observers()
        } else {
            alertDialog(
                getString(R.string.no_internet),
                getString(R.string.no_internet_try),
                { okClick() },
                { cancelClick() })
        }
    }

    private fun okClick() {
        setupRecyclerView()
        observers()
    }

    private fun cancelClick() {
        activity?.finish()
    }

    private fun setupRecyclerView() {
        adapter = ListPagingDataAdapter(this)
        binding.recyclerView.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
            binding.swipeRefresh.isRefreshing = false
        }

        adapter.addLoadStateListener { loadState ->
            // .mediator or .source decide here
            binding.loader.isVisible = loadState.source.refresh is LoadState.Loading
            // remove below line for db and mediator
            binding.recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
        }

        binding.recyclerView.adapter = adapter.withLoadStateFooter(
            footer = ListLoadStateAdapter(adapter)
        )
    }

    private fun observers() {
        listViewModel.users.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }
    }

    override fun userClick(user: User) {
        sharedViewModel.user = user
        navigateTo(R.id.detailFragment)
    }

}