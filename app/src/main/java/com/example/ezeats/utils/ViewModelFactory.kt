package com.example.ezeats.utils

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ezeats.addrecipe.AddRecipeViewModel
import com.example.ezeats.detailrecipe.DetailRecipeViewModel
import com.example.ezeats.home.HomeViewModel
import com.example.ezeats.searchRecipe.SearchViewModel

class ViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        Log.d("ViewModelFactory", "Creating instance of ${modelClass.simpleName}")
        return when{
            modelClass.isAssignableFrom(HomeViewModel::class.java)->{
                HomeViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(AddRecipeViewModel::class.java)->{
                AddRecipeViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(DetailRecipeViewModel::class.java)->{
                DetailRecipeViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(SearchViewModel::class.java)->{
                SearchViewModel(Injection.provideRepository(context)) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}