package com.example.and_practice.ui.main.splash

sealed class SplashEventState {
    data object NavigateToMain : SplashEventState()
    data class ShowError(val message: String) : SplashEventState()
}
