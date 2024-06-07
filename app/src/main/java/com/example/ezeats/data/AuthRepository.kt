package com.example.ezeats.data

import com.example.ezeats.utils.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginUser(email:String, password:String): Flow<Resource<AuthResult>>
    fun registUser(email:String, password:String): Flow<Resource<AuthResult>>
}