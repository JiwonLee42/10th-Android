package com.example.and_practice.data.remote.dto

data class AuthTokenResponseDTO(
    val accessToken: String,
    val refreshToken: String,
    val userId: Long,
    val email: String,
    val role: String
)
