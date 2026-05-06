package com.example.and_practice.data.remote.dto

data class MyFollowingsResponseDTO(
    val followingCount: Long,
    val items: List<FollowingItemDTO>
)
