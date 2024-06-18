package com.example.ezeats.detailrecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.example.ezeats.databinding.FragmentDetailRecipeBinding
import com.example.ezeats.utils.ViewModelFactory
import com.google.android.material.tabs.TabLayout

class DetailRecipeFragment : Fragment() {
    private var _binding: FragmentDetailRecipeBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel: DetailRecipeViewModel by viewModels {
        ViewModelFactory(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailRecipeBinding.inflate(inflater, container, false)

        val viewPager : ViewPager = binding.viewPager
        val tabLayout: TabLayout = binding.tabLayout

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}