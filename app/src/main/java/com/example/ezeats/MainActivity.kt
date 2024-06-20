package com.example.ezeats

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.ezeats.addrecipe.AddRecipeFragment
import com.example.ezeats.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var navController: NavController
    private lateinit var bottomNav : BottomNavigationView
    private lateinit var homeFragment: HomeFragment
    private lateinit var addRecipeFragment: AddRecipeFragment
    @SuppressLint("CutPasteId")

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
                zoomY.doOnEnd { screen.remove() }

                zoomX.start()
                zoomY.start()
            }
        }

        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = navHostFragment.navController

        bottomNav = findViewById(R.id.bottomNavigationView)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    Log.d("Item", "home")
                    navController.navigate(R.id.homeFragment)
//                    loadFragment(HomeFragment())
                    true
                }
                R.id.search -> {
                    Log.d("Item", "search")
                    navController.navigate(R.id.searchFragment)
//                    loadFragment(SearchFragment())
                    true
                }
                R.id.add -> {
                    Log.d("Item", "add recipe")
                    navController.navigate(R.id.addRecipeFragment)
//                    loadFragment(AddRecipeFragment())
                    true
                }
                R.id.saved -> {
                    //navController.navigate(R.id.saved)
                    true
                }
                R.id.account -> {
                    Log.d("Item", "profile")
                    navController.navigate(R.id.profileFragment)
//                    loadFragment(ProfileFragment())
                    true
                }
                else -> {false}
            }
        }

        for (i in 0 until bottomNav.menu.size()) {
            val menuItem = bottomNav.menu.getItem(i)
            menuItem.title = ""
        }

        if (savedInstanceState == null) {
            navController.navigate(R.id.homeFragment)
        }
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit();
    }
}