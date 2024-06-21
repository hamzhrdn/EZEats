package com.example.ezeats.addrecipe

import androidx.lifecycle.ViewModel
import com.example.ezeats.database.Repository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AddRecipeViewModel(private val repository: Repository) : ViewModel() {
    fun addRecipe(file: MultipartBody.Part, content: RequestBody) =
        repository.addRecipe(file, content)
}