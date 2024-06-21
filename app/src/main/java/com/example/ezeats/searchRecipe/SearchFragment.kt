package com.example.ezeats.searchRecipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ezeats.R
import com.example.ezeats.databinding.FragmentHomeBinding
import com.example.ezeats.databinding.FragmentSearchBinding
import com.example.ezeats.home.HomeFragmentDirections
import com.example.ezeats.home.HomeViewModel
import com.example.ezeats.home.RecipeAdapter
import com.example.ezeats.home.TrendingAdapter
import com.example.ezeats.response.SearchItem
import com.example.ezeats.utils.ViewModelFactory

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var searchAdapter: SearchAdapter

    private val searchViewModel: SearchViewModel by viewModels {
        ViewModelFactory(requireActivity())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        searchAdapter = SearchAdapter{ recipe, imageView, nameView, descView ->
            val id = recipe.id

            val action = SearchFragmentDirections.actionSearchFragmentToDetailRecipeFragment(id)
            findNavController().navigate(action)
        }
        val recyclerView = binding.rvRecipe
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        binding.rvRecipe.adapter = searchAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel.recipe.observe(viewLifecycleOwner){ data ->
            Log.d("SearchFragment", "Received data: $data")
            if(data!=null){
                Log.d("SearchFragment", "Data is not null, submitting to adapter")
                searchAdapter.submitData(viewLifecycleOwner.lifecycle, data)
                Log.d("SearchFragment", "Data loaded: $data items")
            }else{
                Log.d("SearchFragment", "No data loaded")
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
        Log.d("SearchFragment", "Destroying SearchFragment view")
        _binding = null
    }

    override fun onPause() {
        super.onPause()
    }
}