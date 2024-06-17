package com.example.ezeats.network

import com.example.ezeats.response.GetAllStoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("recipe")
    suspend fun getRecipe(
        @Query("page") page: Int,
        @Query("size") size: Int
    ):GetAllStoryResponse
}