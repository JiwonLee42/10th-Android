package com.example.and_practice.adapter

import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.example.and_practice.data.ProductData
import com.example.and_practice.databinding.WishItemBinding

class WishItemViewHolder(
    private val binding: WishItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductData) {
        binding.wishProductImage.load(product.productImage) {
            crossfade(true)
        }
        binding.wishProductName.text = product.name
        binding.wishProductSubName.text = product.subName
        binding.wishProductColorCount.text = "${product.colorCount} Colours"
        binding.wishProductPrice.text = "US$${product.price}"
    }
}
