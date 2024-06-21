package com.example.ezeats.login

import androidx.lifecycle.ViewModel
import com.example.ezeats.database.Repository

class LoginViewModel(private val repository: Repository) : ViewModel() {
    fun login(email: String, password: String) = repository.postLogin(email, password)
}