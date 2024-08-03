package com.google.assign.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.google.assign.base.BaseRepository

class Repository(private val apiService: ApiService) : BaseRepository() {

    fun getUsersList() = Pager(PagingConfig(pageSize = 10)) {
        UserPagingSource(apiService)
    }

    suspend fun getUser() = safeApiCall { apiService.getUser() }

}