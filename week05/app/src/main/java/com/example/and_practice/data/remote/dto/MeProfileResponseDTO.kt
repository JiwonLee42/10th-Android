package com.example.and_practice.data.remote.dto

data class MeProfileResponseDTO(
    val nickname: String,
    val profileImageUrl: String,
    val memberBenefitLabel: String,
    val memberBenefitAvailableFrom: String,
    val followingCount: Long,
    val followings: List<FollowingPreviewDTO>
)
