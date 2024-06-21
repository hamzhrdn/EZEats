package com.example.ezeats.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ezeats.R
import com.example.ezeats.databinding.TrendingListBinding
import com.example.ezeats.response.TrendingItem
import com.squareup.picasso.Picasso

class TrendingAdapter (private val callback: (recipe: TrendingItem, imageView: View, nameView: View) -> Unit) : PagingDataAdapter<TrendingItem, TrendingViewHolder>(
    DIFF_CALLBACK
){

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val item = getItem(position)
        Log.d("TrendingAdapter", "onBindViewHolder called for position $position")

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
            holder.bindTrending(item)
        }

        Log.d("Recipe Adapter", "Data Logged")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val view = TrendingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendingViewHolder(view)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TrendingItem>() {
            override fun areItemsTheSame(oldItem: TrendingItem, newItem: TrendingItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TrendingItem, newItem: TrendingItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}

class TrendingViewHolder(val view: TrendingListBinding) : RecyclerView.ViewHolder(view.root) {
    fun bindTrending(item: TrendingItem) {
        view.nameRecipe.text = item.title.toString()

        if (item.images.isNullOrEmpty() || item.images == "") {
            view.imageRecipe.setImageResource(R.mipmap.base_food_image_foreground)
        } else {
            Picasso.get().load(item.images).into(view.imageRecipe)
        }
    }
}