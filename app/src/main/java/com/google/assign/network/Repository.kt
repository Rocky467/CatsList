package com.google.assign.network

import androidx.paging.Pager
import androidx.paging.PagingConfig

class Repository(private val apiService: ApiService) {

    fun getUsersList() = Pager(PagingConfig(pageSize = 10)) {
        UserPagingSource(apiService)
    }

}