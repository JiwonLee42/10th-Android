package com.example.and_practice.ui.home

import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.example.and_practice.ui.home.RecentItemData
import com.example.and_practice.databinding.RecentItemBinding

class RecentItemViewHolder(val binding: RecentItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(recentItem: RecentItemData) {
        binding.recentProductName.text = recentItem.name
        binding.recentProductPrice.text = recentItem.price

        binding.recentProductImage.load(recentItem.productImage) {
            crossfade(true)
        }
    }


}