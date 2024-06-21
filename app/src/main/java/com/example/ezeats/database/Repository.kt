package com.example.ezeats.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.ezeats.network.ApiService
import com.example.ezeats.network.RecipePaging
import com.example.ezeats.network.TrendingPaging
import com.example.ezeats.network.UserRecipePaging
import com.example.ezeats.response.AddRecipeResponse
import com.example.ezeats.response.CategoryFilterItem
import com.example.ezeats.response.DetailRecipeResponse
import com.example.ezeats.response.LoginResponse
import com.example.ezeats.response.RegisterResponse
import com.example.ezeats.response.SearchItem
import com.example.ezeats.response.TrendingItem
import com.example.ezeats.utils.Result
import okhttp3.RequestBody

class Repository (private val apiService: ApiService){

    fun postLogin(email: String, password: String): LiveData<Result<LoginResponse>> = liveData{
        emit(Result.Loading)
        try {
            val response = apiService.postLogin(email, password)
            emit(Result.Success(response))
        }catch (e: Exception){
            Log.e("LoginViewModel", "postLogin: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun postRegister(name: String, email: String, password: String): LiveData<Result<RegisterResponse>> = liveData{
        emit(Result.Loading)
        try {
            val response = apiService.postRegister(name, email, password)
            emit(Result.Success(response))
        }catch (e: Exception){
            Log.e("RegisterViewModel", "postRegister: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getRecipeList(): LiveData<PagingData<CategoryFilterItem>> {
        return Pager(
            config = PagingConfig(
               pageSize = 5
           ),
           pagingSourceFactory = {
               RecipePaging(apiService)
           }
        ).liveData
    }

    fun getTrending(): LiveData<PagingData<TrendingItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                TrendingPaging(apiService)
            }
        ).liveData
    }

    suspend fun getDetailRecipe(id: String): DetailRecipeResponse? {
        val response = apiService.detailRecipe(id)
        return if (response.isSuccessful) response.body() else null
    }

    fun addRecipe(requestBody: RequestBody): LiveData<Result<AddRecipeResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.addRecipe(requestBody)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("AddRecipeViewModel", "AddRecipe: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getSearchRecipe(): LiveData<PagingData<SearchItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                UserRecipePaging(apiService)
            }
        ).liveData
    }
}