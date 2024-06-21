package com.example.ezeats.accountmanager

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ezeats.MainActivity
import com.example.ezeats.MainViewModel
import com.example.ezeats.R
import com.example.ezeats.databinding.ActivitySignInBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.example.ezeats.utils.Preferences

class SignInActivity : AppCompatActivity() {
    private var binding:ActivitySignInBinding? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        auth = Firebase.auth
        binding?.tvRegister?.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }
        binding?.tvForgotPassword?.setOnClickListener {
            startActivity(Intent(this,ForgetPasswordActivity::class.java))
        }

        binding?.btSignIn?.setOnClickListener {
            signInUser()
        }
    }

    private fun signInUser() {
        val email = binding?.etSinInEmail?.text.toString()
        val password = binding?.etSinInPassword?.text.toString()
        if (validateForm(email, password)) {
            showLoading(true)
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task->
                    if (task.isSuccessful){
                        showLoading(false)
//                        Preferences.saveToken(result.data.loginResult?.token!!, this)
                        startActivity((Intent(this, MainActivity::class.java)))
                        finish()
                    } else {
                        showLoading(false)
                        Toast.makeText(this, "Failed to sign in!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun validateForm(email:String, password:String):Boolean
    {
        return when {
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