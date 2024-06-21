package com.example.ezeats

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.ezeats.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private var binding:ActivitySplashBinding? = null
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.isReady.value
            }
            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.4f,
                    0.0f
                )
                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = 500L
                zoomX.doOnEnd { screen.remove() }

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.4f,
                    0.0f
                )
                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = 500L
                zoomY.doOnEnd {
                    screen.remove()
                    nextActivity()
                }

                zoomX.start()
                zoomY.start()
            }
        }
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }
    private fun nextActivity() {
//        val auth = Firebase.auth
//        if (auth.currentUser != null) {
//            startActivity(Intent(this, MainActivity::class.java))
//        } else {
//            startActivity(Intent(this, SignInActivity::class.java))
//        }
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}