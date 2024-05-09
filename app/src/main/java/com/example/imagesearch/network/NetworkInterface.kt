package com.example.imagesearch.network

import com.example.imagesearch.recyclerview.Data
import com.example.imagesearch.network.NetworkClient.AUTH_HEADER
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NetworkInterface  {
    @GET("v2/search/image")
    suspend fun getData(
        @Header("Authorization") apiKey: String = "KakaoAK $AUTH_HEADER",
        @Query("query") query: String,
        @Query("sort") sort : String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Data
}