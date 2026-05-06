package com.example.and_practice.ui.buy

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.example.and_practice.R
import com.example.and_practice.ui.buy.ProductData
import com.example.and_practice.databinding.ProductItemBinding

class ProductViewHolder(
    private val binding: ProductItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductData, onHeartClick: (ProductData) -> Unit) {
        with(binding) {
            productImage.load(product.productImage) {
                crossfade(true)
            }
            productName.text = product.name
            productSubName.text = product.subName
            productColorCount.text = "${product.colorCount} Colours"
            productPrice.text = "US$${product.price}"

            productLikeButton.setImageResource(
                if(product.isLiked) {
                    R.drawable.ic_heart
                } else {
                    R.drawable.ic_heartstraight
                }
            )
            productLikeButton.setOnClickListener {
                onHeartClick(product)
            }
            // Best Seller 문구 표시 여부 결정
            productBadge.visibility =
                if (product.badge == BadgeType.BEST_SELLER) View.VISIBLE else View.GONE
        }
    }
}
