package com.example.and_practice.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.example.and_practice.data.BadgeType
import com.example.and_practice.data.ProductData
import com.example.and_practice.databinding.ProductItemBinding

class ProductViewHolder(
    private val binding: ProductItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductData) {
        binding.productImage.load(product.productImage) {
            crossfade(true)
        }
        binding.productName.text = product.name
        binding.productSubName.text = product.subName
        binding.productColorCount.text = "${product.colorCount} Colours"
        binding.productPrice.text = "US$${product.price}"
        // Best Seller 문구 표시 여부 결정
        binding.productBadge.visibility =
            if (product.badge == BadgeType.BEST_SELLER) View.VISIBLE else View.GONE
    }
}
