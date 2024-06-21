package com.example.ezeats.network

import com.example.ezeats.response.AddRecipeResponse
import com.example.ezeats.response.DetailRecipeResponse
import com.example.ezeats.response.HomeResponse
import com.example.ezeats.response.SearchResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun postRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("home")
    suspend fun getTrending(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): HomeResponse

    @GET("home")
    suspend fun getRecipeList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): HomeResponse

    @POST("add-recipe")
    suspend fun addRecipe(@Body requestBody: RequestBody): AddRecipeResponse

    @GET("recipe/{id}")
    suspend fun detailRecipe(@Path("id") id: String): Response<DetailRecipeResponse>

    @GET("search-by-ingredients")
    suspend fun getSearchRecipeList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): SearchResponse

    @GET("saved-recipes")
    suspend fun savedRecipes(): LikedResponse
}