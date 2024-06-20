package com.example.ezeats.detailrecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ezeats.R

class StepsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var stepsAdapter: StepsAdapter
    private lateinit var viewModel: DetailRecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_steps, container, false)

        recyclerView = view.findViewById(R.id.rvSteps)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val bundle = arguments
        val steps = bundle!!.getString("steps")
        val stepsList = steps!!.split("\n")
        val stepsAdapter = IngredientsAdapter(stepsList)

        recyclerView.adapter = stepsAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[DetailRecipeViewModel::class.java]

        viewModel.steps.observe(viewLifecycleOwner, Observer { steps ->
            // Use the steps data
            val textView = view.findViewById<TextView>(R.id.tvSteps)
            textView.text = steps
        })
    }
}