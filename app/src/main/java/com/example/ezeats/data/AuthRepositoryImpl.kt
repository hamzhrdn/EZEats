package com.example.ezeats.data

import com.example.ezeats.utils.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl : AuthRepository {
    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
        TODO("Not yet implemented")
    }

    override fun registUser(email: String, password: String): Flow<Resource<AuthResult>> {
        TODO("Not yet implemented")
    }
}