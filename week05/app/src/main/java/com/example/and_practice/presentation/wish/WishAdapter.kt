package com.example.and_practice.presentation.wish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.and_practice.presentation.buy.ProductData
import com.example.and_practice.databinding.WishItemBinding

class WishAdapter(
    private val onItemClick: (ProductData) -> Unit = {}
) : ListAdapter<ProductData, WishItemViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishItemViewHolder {
        val binding = WishItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WishItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ProductData>() {
            override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
                return oldItem == newItem
            }
        }
    }
}
