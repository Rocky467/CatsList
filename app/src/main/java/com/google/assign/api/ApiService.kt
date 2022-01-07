package com.google.assign.api

import com.google.assign.model.UserData
import com.google.assign.utils.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("random_user?size=30")
    suspend fun getUsers(): Response<UserData>

    companion object {

        fun getApiService(): ApiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }


}
