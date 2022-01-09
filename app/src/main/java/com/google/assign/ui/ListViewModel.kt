package com.google.assign.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.google.assign.model.User
import com.google.assign.network.ApiService
import com.google.assign.network.RemoteDataSource

class ListViewModel(private val apiService: ApiService) : ViewModel() {

    val usersList = Pager(PagingConfig(pageSize = 10)) {
        RemoteDataSource(apiService)
    }.liveData.cachedIn(viewModelScope)

    //  -- OR--

    fun getUsers(): LiveData<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { RemoteDataSource(apiService) }
        ).liveData
    }

}
