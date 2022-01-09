package com.google.assign.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.google.assign.network.ApiService
import com.google.assign.network.RemoteDataSource

class ListViewModel(private val apiService: ApiService) : ViewModel() {

    val usersList = Pager(PagingConfig(pageSize = 10)) {
        RemoteDataSource(apiService)
    }.liveData.cachedIn(viewModelScope)

}
