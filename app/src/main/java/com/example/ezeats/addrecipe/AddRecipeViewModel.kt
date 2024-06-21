package com.example.ezeats.addrecipe

import androidx.lifecycle.ViewModel
import com.example.ezeats.database.Repository
import okhttp3.MultipartBody

class AddRecipeViewModel(private val repository: Repository) : ViewModel() {
    fun addRecipe(requestBody: MultipartBody) = repository.addRecipe(requestBody)
}