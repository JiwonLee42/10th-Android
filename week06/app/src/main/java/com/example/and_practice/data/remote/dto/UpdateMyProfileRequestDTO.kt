package com.example.and_practice.data.remote.dto

data class UpdateMyProfileRequestDTO(
    val nickname: String? = null,
    val profileImageUrl: String? = null,
    val profileImageKey: String? = null
)
