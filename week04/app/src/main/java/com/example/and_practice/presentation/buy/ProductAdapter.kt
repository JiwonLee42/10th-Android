package com.example.and_practice.presentation.buy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.and_practice.databinding.ProductItemBinding

class ProductAdapter(
    private val onItemClick: (ProductData) -> Unit = {},
    private val onHeartClick: (ProductData) -> Unit = {}
) : ListAdapter<ProductData, ProductViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(
            product = item,
            onHeartClick = onHeartClick
        )
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
