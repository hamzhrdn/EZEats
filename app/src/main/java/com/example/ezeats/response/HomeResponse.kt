package com.example.ezeats.response

data class HomeResponse(
	val trending: List<TrendingItem> = emptyList(),
	val categoryFilter: List<CategoryFilterItem> = emptyList(),
	val categories: List<String?>? = null,
	val savedRecipes: List<Any> = emptyList()
)

data class CategoryFilterItem(
	val images: String? = null,
	val createdby: String? = null,
	val isSaved: Boolean? = null,
	val ingredients: String? = null,
	val id: String? = null,
	val category: String? = null,
	val title: String? = null,
	val steps: String? = null,
	val likes: Int? = null
)

data class TrendingItem(
	val images: String? = null,
	val createdby: String? = null,
	val isSaved: Boolean? = null,
	val ingredients: String? = null,
	val id: String? = null,
	val category: String? = null,
	val title: String? = null,
	val steps: String? = null,
	val likes: Int? = null
)

