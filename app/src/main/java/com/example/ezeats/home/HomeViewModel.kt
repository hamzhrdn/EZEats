package com.example.ezeats.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.ezeats.database.Repository
import com.example.ezeats.response.RecipeItem

class HomeViewModel(repository: Repository) : ViewModel() {
    val recipe: LiveData<PagingData<RecipeItem>> = repository.getRecipe().cachedIn(viewModelScope)
}