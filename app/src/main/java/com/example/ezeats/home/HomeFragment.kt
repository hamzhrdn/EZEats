package com.example.ezeats.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ezeats.databinding.FragmentHomeBinding
import com.example.ezeats.utils.ViewModelFactory

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var trendingAdapter: TrendingAdapter

    private val homeViewModel: HomeViewModel by viewModels {
        ViewModelFactory(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        recipeAdapter = RecipeAdapter{ recipe, imageView, nameView ->
            val id = recipe.id

            val action = HomeFragmentDirections.actionHomeFragmentToDetailRecipeFragment(id)
            findNavController().navigate(action)
        }

        trendingAdapter = TrendingAdapter{recipe, imageView, nameView ->
            val id = recipe.id

            val action = HomeFragmentDirections.actionHomeFragmentToDetailRecipeFragment(id)
            findNavController().navigate(action)
        }


//        val recyclerView = binding.rvRecipe
//        recyclerView.layoutManager = GridLayoutManager(context, 2)
//        binding.rvRecipe.adapter = recipeAdapter

        val recyclerTrending = binding.rvTrending
        recyclerTrending.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvTrending.adapter = trendingAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.recipe.observe(viewLifecycleOwner){ data ->
            Log.d("HomeFragment", "Received data: $data")
            if(data!=null){
                Log.d("HomeFragment", "Data is not null, submitting to adapter")
                recipeAdapter.submitData(viewLifecycleOwner.lifecycle, data)
                Log.d("HomeFragment", "Data loaded: $data items")
            }else{
                Log.d("HomeFragment", "No data loaded")
            }
        }

        homeViewModel.trending.observe(viewLifecycleOwner){ data ->
            Log.d("HomeFragment", "Received data: $data")
            if(data!=null){
                Log.d("HomeFragment", "Data is not null, submitting to adapter")
                trendingAdapter.submitData(viewLifecycleOwner.lifecycle, data)
                Log.d("HomeFragment", "Data loaded: $data items")
            }else{
                Log.d("HomeFragment", "No data loaded")
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    requireActivity().finishAffinity()
                }
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("HomeFragment", "Destroying HomeFragment view")
        _binding = null
    }

    override fun onPause() {
        super.onPause()
    }
}