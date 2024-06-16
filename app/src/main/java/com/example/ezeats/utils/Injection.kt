package com.example.ezeats.utils

import android.content.Context
import com.example.ezeats.database.Repository
import com.example.ezeats.network.ApiConfig

object Injection {
    fun provideRepository(context: Context): Repository {
        val apiService = ApiConfig.getApiService(context)
        return Repository(apiService)
    }
}