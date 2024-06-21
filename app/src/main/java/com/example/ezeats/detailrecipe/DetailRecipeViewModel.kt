package com.example.ezeats.detailrecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ezeats.database.Repository
import com.example.ezeats.response.DetailRecipeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailRecipeViewModel(private val repository: Repository) : ViewModel() {
    private val _steps = MutableLiveData<String>()
    private val _ingredients = MutableLiveData<String>()

    val steps: LiveData<String> = _steps
    val ingredients: LiveData<String> = _ingredients

    private val _recipe = MutableLiveData<DetailRecipeResponse>()
    val recipe: LiveData<DetailRecipeResponse> = _recipe

    fun setStepsAndIngredients(steps: String, ingredients: String) {
        _steps.value = steps
        _ingredients.value = ingredients
    }

    private val _detailRecipeResponse = MutableLiveData<DetailRecipeResponse>()
    val detailRecipeResponse: LiveData<DetailRecipeResponse> = _detailRecipeResponse

//    fun getDetailRecipe(id: String) {
//        viewModelScope.launch {
//            Log.d("DetailRecipeViewModel", "Coroutine started")
//            try {
//                val response = repository.getDetailRecipe(id)
//                Log.d("DetailRecipeViewModel", "Response: $response")
//                response?.let {
//                    _detailRecipeResponse.value = it
////                    Log.d("DetailRecipeViewModel", "Data fetched: $it")
//                    Log.d("DetailRecipeViewModel", "LiveData updated: $it")
//                }
//
//            } catch (e: Exception) {
//                // handle error
//            }
//        }
//    }
    suspend fun getDetailRecipe(id: String): DetailRecipeResponse? {
    return withContext(Dispatchers.IO) {
        repository.getDetailRecipe(id)
    }
}
}