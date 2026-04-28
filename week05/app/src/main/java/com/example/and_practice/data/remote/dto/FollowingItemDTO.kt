package com.example.and_practice.data.remote.dto

data class FollowingItemDTO(
    val userId: Long,
    val nickname: String,
    val profileImageUrl: String,
    val isFollowing: Boolean
)
