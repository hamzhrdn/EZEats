package com.example.ezeats.detailrecipe

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import com.example.ezeats.R
import com.example.ezeats.databinding.FragmentDetailRecipeBinding
import com.example.ezeats.utils.ViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso

class DetailRecipeFragment : Fragment() {
    private var _binding: FragmentDetailRecipeBinding? = null
    private val binding get() = _binding!!

    private val args: DetailRecipeFragmentArgs by navArgs()

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

        val id = args.id
        Log.d("Detail Recipe Fragment", "Load recipe id $id")
        detailViewModel.getDetailRecipe(id)
        detailViewModel.recipe.observe(viewLifecycleOwner){ recipe ->
            Log.d("DetailRecipeFragment", "Observer triggered: $recipe")
            if (recipe!=null){
                binding.tvTitle.text = recipe.title
                binding.tvLikes.text = recipe.likes.toString()
                Picasso.get().load(recipe.images).placeholder(R.mipmap.base_food_image_foreground).into(binding.ivProfile)
                Log.d("Detail Recipe Fragment", "Load Recipe")
            }else{
                Log.d("Detail Recipe Fragment", "Data null")
            }
        }
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