package com.example.and_practice.ui.wish

import com.example.and_practice.ui.wish.model.WishItemUiModel

data class WishUiState(
    val isLoading: Boolean = false,
    val items: List<WishItemUiModel> = emptyList(),
    val errorMessage: String? = null,
)
