package com.example.and_practice.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
data object Splash
@Serializable
data class Home(val title: String)

@Serializable
data object MyPage

@Serializable
data object Cart

@Serializable
data object Wish

@Serializable
data object Purchase
