package com.google.assign.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.assign.network.ApiService
import com.google.assign.model.User
import com.google.assign.utils.log

class PagingAdapter(private val apiService: ApiService) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {

        try {
            val nextPageNumber = params.key ?: 1
            val response = apiService.getUsers(30)
            val responseData = mutableListOf<User>()
            val data = response.body()?: emptyList()
            log("$data")
            responseData.addAll(data)

            val prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = nextPageNumber.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition
    }

}
