package com.google.assign.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.assign.model.Cats
import com.google.assign.utils.Const.PAGE_LIMIT
import retrofit2.HttpException

class ListPagingSource(private val apiService: ApiService) : PagingSource<Int, Cats>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cats> {
        return try {
            val page = params.key ?: 1

            val response = apiService.getCats(order = "Asc", page = page, limit = PAGE_LIMIT)

            if (!response.isSuccessful) {
                throw HttpException(response)
            }

            val cats = response.body().orEmpty()

            LoadResult.Page(
                data = cats,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (cats.size < PAGE_LIMIT) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Cats>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}
