package com.example.ezeats.splashscreen

import android.animation.Animator
import android.animation.AnimatorInflater
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.ezeats.MainActivity
import com.example.ezeats.R
import com.example.ezeats.databinding.FragmentSplashBinding
import com.example.ezeats.utils.Preferences
import com.google.android.material.bottomnavigation.BottomNavigationView


class SplashFragment : Fragment() {
    private var _binding : FragmentSplashBinding? = null
    private lateinit var navigationView : BottomNavigationView
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animatedLogo = binding.animatedLogo
        animatedLogo.setImageResource(R.drawable.animated_logo)

        val animator = AnimatorInflater.loadAnimator(context, R.animator.logo_animator)
        animator.setTarget(animatedLogo)
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                navigateToNextFragment()
            }

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationRepeat(animation: Animator) {}
        })
        animator.start()
    }

    private fun navigateToNextFragment() {
        val sharedPreferences = Preferences.initPref(requireContext(), "Login")
        val token = sharedPreferences.getString("token", "")

        if (token!= "") {
            val action = SplashFragmentDirections.actionSplashFragment2ToHomeFragment()
            findNavController().navigate(action)
        } else {
            val action = SplashFragmentDirections.actionSplashFragment2ToLoginFragment()
            findNavController().navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            //(activity as MainActivity).navBar.visibility = View.GONE
        }
        catch (_: Exception) {

        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}