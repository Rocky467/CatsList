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
        setupViews()
        observers()
    }

    private fun setupViews() {
        adapter = ListAdapter(this)

        binding.apply {
            recyclerView.adapter = adapter

            swipeRefresh.setOnRefreshListener {
                adapter.refresh()
                swipeRefresh.isRefreshing = false
            }

            adapter.addLoadStateListener { loadState ->
                loader.isVisible = loadState.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.refresh is LoadState.NotLoading
            }

            recyclerView.adapter = adapter.withLoadStateFooter(
                footer = ListLoadStateAdapter(adapter)
            )
        }
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