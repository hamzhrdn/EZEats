package com.example.ezeats.detailrecipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private val fragments: MutableList<Fragment> = mutableListOf()
    private val fragmentBundles: MutableList<Bundle> = mutableListOf()

    fun addFragment(fragment: Fragment, bundle: Bundle) {
        fragments.add(fragment)
        fragmentBundles.add(bundle)
        fragment.arguments = bundle
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {

        return fragments[position]
    }
}