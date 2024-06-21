package com.example.ezeats.detailrecipe

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.ezeats.databinding.FragmentDetailRecipeBinding
import com.example.ezeats.utils.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class DetailRecipeFragment : Fragment() {
    private var _binding: FragmentDetailRecipeBinding? = null
    private val binding get() = _binding!!

    private val args: DetailRecipeFragmentArgs by navArgs()

    private val detailViewModel: DetailRecipeViewModel by viewModels {
        ViewModelFactory(requireActivity())
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailRecipeBinding.inflate(inflater, container, false)
        val id = args.id
        val bundle = Bundle()
        Log.d("Detail Recipe Fragment", "Load recipe id $id")

        lifecycleScope.launch {
            try {
                val recipe = detailViewModel.getDetailRecipe(id)
                if (recipe!= null) {
                    binding.tvName.text = recipe.title
                    binding.tvLikes.text = "${recipe.likes} Likes"
                    Picasso.get().load(recipe.images).into(binding.ivProfile)

                    bundle.putString("ingredients", recipe.ingredients)
                    bundle.putString("steps", recipe.steps)
                    Log.d("SourceFragment", "Setting bundle: $bundle")

                    Log.d("Detail Recipe Fragment", bundle.getString("ingredients")!!)

                    val ingredientsFragment = IngredientsFragment()
                    val stepsFragment = StepsFragment()

                    ingredientsFragment.arguments = bundle
                    stepsFragment.arguments = bundle

                    val fragments = mutableListOf<Fragment>(
                        IngredientsFragment(),
                        StepsFragment()
                    )

                    val titleFragment = mutableListOf(
                        "Ingredients",
                        "Steps"
                    )

                    val pagerAdapter = SectionPagerAdapter(requireActivity())
                    fragments.forEach { pagerAdapter.addFragment(it, bundle) }
                    binding.viewPager2.adapter = pagerAdapter
                    binding.viewPager2.adapter

                    TabLayoutMediator(binding.tabLayout, binding.viewPager2){
                            tab, position -> tab.text = titleFragment[position]
                    }.attach()

                    Log.d("DetailRecipeFragment", "Load Recipe")
                } else {
                    Log.d("DetailRecipeFragment", "Data null")
                }
            } catch (e: Exception) {
                // handle error
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