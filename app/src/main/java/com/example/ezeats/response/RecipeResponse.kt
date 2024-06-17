package com.example.ezeats.response

import androidx.room.Entity
import androidx.room.PrimaryKey

data class GetAllStoryResponse(
    val listRecipe: List<RecipeItem> = emptyList(),
    val error: Boolean,
    val message: String
)

@Entity(tableName = "recipe")
data class RecipeItem(
    @PrimaryKey
    val id: String,
    val title: String,
    val ingredients: String,
    val step: String
)