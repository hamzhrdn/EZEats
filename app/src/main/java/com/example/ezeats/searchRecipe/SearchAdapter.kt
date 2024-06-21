package com.example.ezeats.searchRecipe

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ezeats.R
import com.example.ezeats.databinding.LongRecipeBinding
import com.example.ezeats.response.SearchItem
import com.squareup.picasso.Picasso

class SearchAdapter (private val callback: (recipe: SearchItem, imageView: View, nameView: View, descView: View) -> Unit) : PagingDataAdapter<SearchItem, SearchViewHolder>(
    DIFF_CALLBACK
) {
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)
        Log.d("SearchAdapter", "onBindViewHolder called for position $position")

        holder.view.root.setOnClickListener{
            item?.let {
                callback.invoke(
                    item,
                    holder.view.imageRecipe,
                    holder.view.nameRecipe,
                    holder.view.descRecipe
                )
            }
        }

        item?.let {
            holder.bindRecipe(item)
        }

        Log.d("Search Adapter", "Data Logged")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LongRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(view)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SearchItem>() {
            override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}

class SearchViewHolder(val view: LongRecipeBinding) : RecyclerView.ViewHolder(view.root) {
    fun bindRecipe(item: SearchItem) {
        view.nameRecipe.text = item.title.toString()
        view.descRecipe.text = item.ingredients.toString()
        if (item.images.isNullOrEmpty() || item.images == "") {
            view.imageRecipe.setImageResource(R.mipmap.base_food_image_foreground)
        } else {
            Picasso.get().load(item.images).into(view.imageRecipe)
        }
    }
}