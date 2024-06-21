package com.example.ezeats.searchRecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.ezeats.database.Repository
import com.example.ezeats.response.CategoryFilterItem
import com.example.ezeats.response.SearchItem
import com.example.ezeats.response.TrendingItem

class SearchViewModel (repository: Repository) : ViewModel() {
    val recipe: LiveData<PagingData<SearchItem>> = repository.getSearchRecipe().cachedIn(viewModelScope)
}