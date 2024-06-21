package com.example.ezeats.response

data class LikedResponse(
    val like: List<LikedItem> = emptyList()
)

data class LikedItem(
    val images: String? = null,
//    val createdby: String,
//    val isSaved: Boolean,
    val ingredients: String,
    val id: String,
    val category: String,
    val title: String,
    val steps: String,
    val likes: Int
)
