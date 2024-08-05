package com.google.assign.ui.list

import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.google.assign.R
import com.google.assign.base.BaseFragment
import com.google.assign.databinding.ListFragmentBinding
import com.google.assign.model.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : BaseFragment<ListFragmentBinding>(ListFragmentBinding::inflate),
    ListAdapter.AdapterInterface {

    private lateinit var adapter: ListAdapter
    private val listViewModel: ListViewModel by viewModel()

    override fun onViewCreated() {
        setupRecyclerView()
        observers()
    }

    private fun setupRecyclerView() {
        adapter = ListAdapter(this)
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
        listViewModel.userList.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }
    }

    override fun userClick(user: User) {
        sharedViewModel.user = user
        navigateTo(R.id.detailFragment)
    }

}