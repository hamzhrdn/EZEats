package com.example.ezeats.detailrecipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ezeats.R

class StepsAdapter(private val steps: List<String>) : RecyclerView.Adapter<StepsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.steps_list, parent, false)
        return StepsViewHolder(view)
    }

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {
        val steps = steps[position]
        holder.bind(steps)
    }

    override fun getItemCount(): Int = steps.size
}

class StepsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(steps: String) {
        view.findViewById<TextView>(R.id.tvSteps).text = steps
    }
}