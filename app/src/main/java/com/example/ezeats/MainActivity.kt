package com.example.ezeats

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ezeats.addrecipe.AddRecipeFragment
import com.example.ezeats.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView
    lateinit var homeFragment: HomeFragment
    lateinit var addRecipeFragment: AddRecipeFragment
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
                R.id.add -> {
                    loadFragment(AddRecipeFragment())
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

    private  fun loadFragment(fragment: Fragment){
        if (fragment!= null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, fragment)
            transaction.commit()
        }
    }
}