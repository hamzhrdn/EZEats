package com.example.ezeats.database

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.ezeats.network.ApiService
import com.example.ezeats.network.RecipePaging
import com.example.ezeats.response.CategoryFilterItem

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
}