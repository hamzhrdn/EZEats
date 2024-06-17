package com.example.ezeats

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ezeats.addrecipe.AddRecipeFragment
import com.example.ezeats.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav : BottomNavigationView
    private lateinit var homeFragment: HomeFragment
    private lateinit var addRecipeFragment: AddRecipeFragment
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottomNavigationView)
        homeFragment = HomeFragment()
        addRecipeFragment = AddRecipeFragment()

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.search -> {
                    //loadFragment()
                    true
                }
                R.id.add -> {
                    loadFragment(AddRecipeFragment())
                    true
                }
                R.id.saved -> {
                    //loadFragment()
                    true
                }
                R.id.account -> {
                    //loadFragment()
                    true
                }
                else -> {false}
            }
        }

        loadFragment(homeFragment)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        for (i in 0 until bottomNavigationView.menu.size()) {
            val menuItem = bottomNavigationView.menu.getItem(i)
            menuItem.title = ""
        }
    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment)
        transaction.commit()
    }
}