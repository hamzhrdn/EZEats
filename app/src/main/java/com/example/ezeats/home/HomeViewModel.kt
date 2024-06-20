package com.example.ezeats.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.ezeats.database.Repository
import com.example.ezeats.response.CategoryFilterItem
import com.example.ezeats.response.TrendingItem

class HomeViewModel(repository: Repository) : ViewModel() {
    val recipe: LiveData<PagingData<CategoryFilterItem>> = repository.getRecipeList().cachedIn(viewModelScope)

    val trending : LiveData<PagingData<TrendingItem>> = repository.getTrending().cachedIn(viewModelScope)
}