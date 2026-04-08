package com.example.and_practice.presentation.wish

import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.example.and_practice.presentation.buy.ProductData
import com.example.and_practice.databinding.WishItemBinding

class WishItemViewHolder(
    private val binding: WishItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductData) {
        with(binding) {
            wishProductImage.load(product.productImage) {
                crossfade(true)
            }
            wishProductName.text = product.name
            wishProductSubName.text = product.subName
            wishProductColorCount.text = "${product.colorCount} Colours"
            wishProductPrice.text = "US$${product.price}"
        }
    }
}
