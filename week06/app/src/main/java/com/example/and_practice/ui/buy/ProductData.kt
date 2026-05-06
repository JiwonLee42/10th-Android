package com.example.and_practice.ui.buy

data class ProductData(
    val id: Int,
    val badge: BadgeType? = null,
    val name: String = "",
    val subName: String = "",
    val colorCount : Int,
    val price: Int,
    val productImage: String? = null,
    val isLiked : Boolean = false
)
