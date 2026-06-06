package com.google.assign.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.google.assign.base.BaseRepository

class Repository(private val apiService: ApiService) : BaseRepository() {

    fun getCatsList() = Pager(PagingConfig(pageSize = 10)) {
        ListPagingSource(apiService)
    }

    suspend fun getCatById(catId: String) = safeApiCall { apiService.getCatById(catId) }

}