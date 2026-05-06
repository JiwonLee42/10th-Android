package com.example.and_practice.ui.home

import com.example.and_practice.ui.home.model.RecentItemUiModel

data class HomeUiState(
    val isLoading: Boolean = false,
    val items: List<RecentItemUiModel> = emptyList(),
    val errorMessage: String? = null,
)
