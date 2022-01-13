package com.google.assign.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.assign.model.User
import com.google.assign.utils.log

class RemoteDataSource(private val apiService: ApiService) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {

        return try {

            val response = apiService.getUsers(20).body() ?: emptyList()
            log("$response")

            val page = params.key ?: 1

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition
    }

}
