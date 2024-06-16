package com.example.ezeats.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ezeats.addrecipe.AddRecipeViewModel
import com.example.ezeats.home.HomeViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        return when{
            modelClass.isAssignableFrom(HomeViewModel::class.java)->{
                HomeViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(AddRecipeViewModel::class.java)->{
                AddRecipeViewModel(Injection.provideRepository(context)) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}