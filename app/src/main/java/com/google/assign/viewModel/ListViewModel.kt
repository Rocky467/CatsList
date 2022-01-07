package com.google.assign.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.google.assign.adapter.PagingAdapter
import com.google.assign.api.ApiService

class ListViewModel(private val apiService: ApiService) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 6)) {
        PagingAdapter(apiService)
    }.flow.cachedIn(viewModelScope)


}
