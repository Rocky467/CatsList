package com.google.assign.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.google.assign.adapter.PagingAdapter
import com.google.assign.network.ApiService
import com.google.assign.model.User

class ListViewModel(private val apiService: ApiService) : ViewModel() {

    fun getUsers(): LiveData<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { PagingAdapter(apiService) }
        ).liveData
    }

    //  -- OR--

    val usersList = Pager(PagingConfig(pageSize = 10)) {
        PagingAdapter(apiService)
    }.liveData.cachedIn(viewModelScope)

}
