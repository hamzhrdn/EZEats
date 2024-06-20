package com.example.ezeats.response

data class DetailRecipeResponse(
	val images: String? = null,
	val createdby: String,
	val ingredients: String,
	val id: String,
	val category: Any? = null,
	val title: String,
	val steps: String,
	val isUserLike: Boolean,
	val likes: Int
)

