package com.example.ezeats.detailrecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ezeats.R
import com.example.ezeats.databinding.FragmentIngredientsBinding
import com.example.ezeats.utils.ViewModelFactory

class IngredientsFragment : Fragment() {
    private var _binding: FragmentIngredientsBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var ingredientsAdapter : IngredientsAdapter
    private val detailViewModel: DetailRecipeViewModel by viewModels {
        ViewModelFactory(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)

        val recyclerView = binding.rvIngredient
        recyclerView.layoutManager = LinearLayoutManager(context)

        val bundle = arguments
        val ingredients = bundle?.getString("ingredients")
        val ingredientsList = ingredients?.split("\n")
        val ingredientsAdapter = ingredientsList?.let { IngredientsAdapter(it) }

        recyclerView.adapter = ingredientsAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel.ingredients.observe(viewLifecycleOwner) { ingredients ->
            val textView = view.findViewById<TextView>(R.id.tvIngredient)
            textView.text = ingredients
        }
    }
}