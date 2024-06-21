package com.example.ezeats.accountmanager

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ezeats.R
import com.example.ezeats.databinding.ActivitySignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.ezeats.MainActivity
import com.example.ezeats.utils.Preferences

class SignUpActivity : AppCompatActivity() {
    private var binding:ActivitySignUpBinding? = null
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        auth = Firebase.auth

        binding?.tvSignIn?.setOnClickListener {
            startActivity(Intent(this,SignInActivity::class.java))
            finish()
        }

        binding?.btnSignUp?.setOnClickListener {signUpUser()}
    }

    private fun signUpUser() {
        val name = binding?.etSinUpName?.text.toString()
        val email = binding?.etSinUpEmail?.text.toString()
        val password = binding?.etSinUpPassword?.text.toString()
        val confirm_password = binding?.etSinUpPassword?.text.toString()

        if (validateForm(name, email, password, confirm_password)) {
            showLoading(true)
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener { task->
                    if (task.isSuccessful){
                        showLoading(false)
                        Toast.makeText(this, "Account is created!",Toast.LENGTH_SHORT).show()
                        startActivity((Intent(this,MainActivity::class.java)))
                        finish()
                    } else {
                        showLoading(false)
                        Toast.makeText(this, "Failed to create account!",Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun validateForm(name:String, email:String, password:String, confirm_password:String):Boolean
    {
        return when {
            TextUtils.isEmpty(name)->{
                binding?.tilName?.error = "Enter your name!"
                false
            }
            TextUtils.isEmpty(email) ->{
                binding?.tilEmail?.error = "Enter your email address!"
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding?.tilEmail?.error = "Enter a valid email address!"
                false
            }
            TextUtils.isEmpty(password)->{
                binding?.tilPassword?.error = "Enter a valid password!"
                false
            }
            password != confirm_password->{
                binding?.tilConfirmPassword?.error = "Passwords don't match!"
                false
            }
            else -> { true }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding?.progressBar?.visibility = View.VISIBLE
        } else {
            binding?.progressBar?.visibility = View.GONE
        }
    }
}