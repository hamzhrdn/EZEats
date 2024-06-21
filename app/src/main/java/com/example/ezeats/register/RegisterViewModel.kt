package com.example.ezeats.register

import androidx.lifecycle.ViewModel
import com.example.ezeats.database.Repository

class RegisterViewModel(private val repository: Repository) : ViewModel() {
    fun register(name: String, email: String, password: String) = repository.postRegister(name, email, password)
}