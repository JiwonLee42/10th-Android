package com.example.and_practice.ui.main.splash

data class SplashUiState(
    val isLoading: Boolean = false,
    val isLoginSuccess: Boolean = false,
    val errorMessage: String? = null,
)
