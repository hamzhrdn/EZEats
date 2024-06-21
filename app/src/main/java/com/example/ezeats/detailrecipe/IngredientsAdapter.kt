package com.example.ezeats.detailrecipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ezeats.R

class IngredientsAdapter(internal var ingredients: List<String>) : RecyclerView.Adapter<IngredientsViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ingredients_list, parent, false)
        return IngredientsViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val ingredient = ingredients[position]
        holder.bind(ingredient)
    }

    override fun getItemCount(): Int = ingredients.size
}

class IngredientsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(ingredient: String) {
        view.findViewById<TextView>(R.id.tvIngredient).text = ingredient
    }
}