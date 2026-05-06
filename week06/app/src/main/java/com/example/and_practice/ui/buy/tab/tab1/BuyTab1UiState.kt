package com.example.and_practice.ui.buy.tab.tab1

import com.example.and_practice.ui.buy.model.ProductUiModel

data class BuyTab1UiState(
    val isLoading: Boolean = false,
    val products: List<ProductUiModel> = emptyList(),
    val errorMessage: String? = null,
)
