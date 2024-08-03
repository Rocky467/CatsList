package com.google.assign.base

import com.google.assign.model.ErrorResponse
import com.google.assign.utils.Resource
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response

abstract class BaseRepository {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {

        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiCall()
                if (response.isSuccessful) {
                    Resource.Success(response.body()!!)
                } else {
                    val errorResponse = getErrorResponse(response.errorBody()!!)
                    Resource.Error(errorResponse?.error ?: "Something went wrong")
                }
            } catch (e: HttpException) {
                Resource.Error(e.message ?: "Http, Something went wrong")
            } catch (e: IOException) {
                Resource.Error(e.message ?: "IO, No internet connection")
            } catch (e: Exception) {
                Resource.Error(e.message ?: "Exception, Something went wrong")
            }
        }
    }

    private fun getErrorResponse(response: ResponseBody): ErrorResponse? {
        return Gson().fromJson(response.string(), ErrorResponse::class.java)
    }

}