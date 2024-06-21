package com.example.ezeats.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.filter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ezeats.databinding.FragmentHomeBinding
import com.example.ezeats.utils.ViewModelFactory

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var trendingAdapter: TrendingAdapter
    private lateinit var allButton: Button
    private lateinit var ayamButton: Button
    private lateinit var kambingButton: Button
    private lateinit var sapiButton: Button
    private lateinit var telurButton: Button
    private lateinit var tahuButton: Button
    private lateinit var ikanButton: Button
    private lateinit var tempeButton: Button

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

        val recyclerView = binding.rvRecipe
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.rvRecipe.adapter = recipeAdapter

        val recyclerTrending = binding.rvTrending
        recyclerTrending.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvTrending.adapter = trendingAdapter

        allButton = binding.btAll
        allButton.setOnClickListener{
            filterDataByCategory("All")
        }
        ayamButton = binding.btAyam
        ayamButton.setOnClickListener{
            filterDataByCategory("ayam")
        }
        sapiButton = binding.btSapi
        sapiButton.setOnClickListener{
            filterDataByCategory("sapi")
        }
        kambingButton = binding.btKambing
        kambingButton.setOnClickListener{
            filterDataByCategory("kambing")
        }
        telurButton = binding.btTelur
        telurButton.setOnClickListener{
            filterDataByCategory("telur")
        }
        tahuButton = binding.btTahu
        tahuButton.setOnClickListener{
            filterDataByCategory("tahu")
        }
        tempeButton = binding.btTempe
        tempeButton.setOnClickListener{
            filterDataByCategory("tempe")
        }
        ikanButton = binding.btIkan
        ikanButton.setOnClickListener{
            filterDataByCategory("ikan")
        }

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

    private fun filterDataByCategory(category: String) {
        homeViewModel.recipe.value?.let { data ->
            val filteredData = data.filter { it.category == category }
            recipeAdapter.submitData(viewLifecycleOwner.lifecycle, filteredData)
        }
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