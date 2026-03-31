package com.example.and_practice.data

data class ProductData(
    val badge: BadgeType? = null,
    val name: String = "",
    val subName: String = "",
    val colorCount : Int,
    val price: Int,
    val productImage: String? = null,
)