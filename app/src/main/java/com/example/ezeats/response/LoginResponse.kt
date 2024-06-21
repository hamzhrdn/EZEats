package com.example.ezeats.response

data class LoginResponse(
    val loginResult: LoginResult? = null,
    val error: Boolean? = null,
    val message: String? = null
)

data class LoginResult(
    val name: String? = null,
    val email: String? = null,
    val accessToken: String? = null
)
