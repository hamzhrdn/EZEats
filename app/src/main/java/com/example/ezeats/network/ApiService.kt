package com.example.ezeats.network

import com.example.ezeats.response.DetailRecipeResponse
import com.example.ezeats.response.HomeResponse
import com.example.ezeats.response.LikedResponse
import com.example.ezeats.response.LoginResponse
import com.example.ezeats.response.RegisterResponse
import com.example.ezeats.response.SearchResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

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

    @GET("search-by-ingredients")
    suspend fun getSearchRecipeList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): SearchResponse

    @POST("add-recipe")
    suspend fun addRecipe()

    @GET("recipe/{id}")
    suspend fun detailRecipe(@Path("id") id: String): Response<DetailRecipeResponse>

    @GET("saved-recipes")
    suspend fun savedRecipes(): LikedResponse
}