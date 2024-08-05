package com.google.assign.network

import com.google.assign.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        private const val USER = "random_user"
    }

    @GET(USER)
    suspend fun getUsers(@Query("size") size: Int): Response<List<User>>

    @GET(USER)
    suspend fun getUser(): Response<User>

}
