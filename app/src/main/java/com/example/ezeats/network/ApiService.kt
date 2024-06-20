package com.example.ezeats.network

import com.example.ezeats.response.HomeResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("home")
    suspend fun getTrending(): HomeResponse

    @GET("home")
    suspend fun getRecipeList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): HomeResponse

    @POST("add-recipe")
    suspend fun addRecipe(

    )
}