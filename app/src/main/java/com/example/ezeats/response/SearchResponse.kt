package com.example.ezeats.response

data class SearchResponse(
    val search: List<SearchItem> = emptyList()
)

data class SearchItem(
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