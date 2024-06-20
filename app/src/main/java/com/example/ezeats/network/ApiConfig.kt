package com.example.ezeats.network

import android.content.Context
import com.example.ezeats.utils.Preferences
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object{
        private fun getInterceptor(token: String?): OkHttpClient {
            val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjBjNWYxOWI5LWU2YTUtNGNhZS1hNTY3LTE5NDhmZjNkYTQ5OSIsImlhdCI6MTcxODc3NjY2NiwiZXhwIjoxNzE5MjA4NjY2fQ.A7sp00PPt-VeCj1yQ45K7DhFkdb2SszlnxacZKjkD9s"
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            return if (token.isNullOrEmpty()) {
                OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()
            } else {
                OkHttpClient.Builder()
                    .addInterceptor(Interceptor(token))
                    .addInterceptor(loggingInterceptor)
                    .build()
            }
        }

        fun getApiService(context: Context): ApiService {

            val sharedPref = Preferences.initPref(context, "onSignIn")
            val token = sharedPref.getString("token", null).toString()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://34.50.78.48:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getInterceptor(token))
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}