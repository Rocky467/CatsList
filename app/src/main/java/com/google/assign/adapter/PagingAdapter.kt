package com.google.assign.adapter

import android.util.Log
import androidx.paging.PagingSource
import com.google.assign.api.ApiService
import com.google.assign.model.User

class PagingAdapter(private val apiService: ApiService) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {

        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = apiService.getUsers()
            val responseData = mutableListOf<User>()
            val data = response.body()
            Log.d("dataHere: ", data.toString())
            responseData.addAll(data!!)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}
