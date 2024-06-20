package com.example.ezeats.response

data class HomeResponse(
	val trending: List<TrendingItem> = emptyList(),
	val categoryFilter: List<CategoryFilterItem> = emptyList(),
	val categories: List<String?>? = null,
	val savedRecipes: List<Any> = emptyList()
)

data class CategoryFilterItem(
	val images: String? = null,
	val createdby: String,
	val isSaved: Boolean,
	val ingredients: String,
	val id: String,
	val category: String,
	val title: String,
	val steps: String,
	val likes: Int
)

data class TrendingItem(
	val images: String? = null,
	val createdby: String,
	val isSaved: Boolean,
	val ingredients: String,
	val id: String,
	val category: String,
	val title: String,
	val steps: String,
	val likes: Int
)

