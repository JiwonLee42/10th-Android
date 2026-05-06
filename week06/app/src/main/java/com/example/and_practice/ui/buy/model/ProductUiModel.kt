package com.example.and_practice.ui.buy.model

import com.example.and_practice.ui.buy.BadgeType

data class ProductUiModel(
    val id: Int,
    val badge: BadgeType? = null,
    val name: String,
    val subName: String,
    val colorCount: Int,
    val price: Int,
    val productImage: String,
    val isLiked: Boolean,
)
