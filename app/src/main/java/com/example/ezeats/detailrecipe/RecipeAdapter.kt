package com.example.ezeats.detailrecipe

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ezeats.R
import com.example.ezeats.databinding.HomeListBinding
import com.example.ezeats.response.CategoryFilterItem
import com.squareup.picasso.Picasso

class RecipeAdapter (private val callback: (recipe: CategoryFilterItem, imageView: View, nameView: View) -> Unit) : PagingDataAdapter<CategoryFilterItem, RecipeViewHolder>(
    DIFF_CALLBACK
){

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val item = getItem(position)
        Log.d("RecipeAdapter", "onBindViewHolder called for position $position")

        holder.view.root.setOnClickListener{
            item?.let {
                callback.invoke(
                    item,
                    holder.view.imageRecipe,
                    holder.view.nameRecipe
                )
            }
        }

        item?.let {
            holder.bind(item)
        }
        Log.d("Recipe Adapter", "Data Logged")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = HomeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(view)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CategoryFilterItem>() {
            override fun areItemsTheSame(oldItem: CategoryFilterItem, newItem: CategoryFilterItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: CategoryFilterItem, newItem: CategoryFilterItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}

class RecipeViewHolder(val view: HomeListBinding) : RecyclerView.ViewHolder(view.root) {
    fun bind(item: CategoryFilterItem) {
        view.nameRecipe.text = item.title.toString()

        if (item.images.isNullOrEmpty() || item.images == "") {
            view.imageRecipe.setImageResource(R.mipmap.base_food_image_foreground)
        } else {
            Picasso.get().load(item.images).into(view.imageRecipe)
        }
    }
}