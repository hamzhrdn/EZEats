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
import com.example.ezeats.response.AddRecipeResponse
import com.example.ezeats.response.CategoryFilterItem
import com.example.ezeats.response.DetailRecipeResponse
import com.example.ezeats.response.TrendingItem
import com.example.ezeats.utils.Result
import okhttp3.MultipartBody

class Repository (private val apiService: ApiService){
//    fun getRecipe(): LiveData<PagingData<RecipeResponseItem>> {
//        return Pager(
//            config = PagingConfig(
//                pageSize = 5
//            ),
//            pagingSourceFactory = {
//                RecipePaging(apiService)
//            }
//        ).liveData
//    }

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

    fun addRecipe(requestBody: MultipartBody):LiveData<Result<AddRecipeResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.addRecipe(requestBody)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("AddRecipeViewModel", "addRecipe: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }
}