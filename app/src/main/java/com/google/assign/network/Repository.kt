package com.google.assign.network

import androidx.paging.Pager
import androidx.paging.PagingConfig

class Repository(private val apiService: ApiService) {

    // pageSize = 10 is the proper value, don't change
    val usersList = Pager(PagingConfig(pageSize = 10)) {
        RemoteDataSource(apiService)
    }

}