package com.example.ezeats.detailrecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailRecipeViewModel : ViewModel() {
    private val _steps = MutableLiveData<String>()
    private val _ingredients = MutableLiveData<String>()

    val steps: LiveData<String> = _steps
    val ingredients: LiveData<String> = _ingredients


    fun setStepsAndIngredients(steps: String, ingredients: String) {
        _steps.value = steps
        _ingredients.value = ingredients
    }
}