package com.example.and_practice.ui.profile

data class ProfileUiState(
    val isLoading: Boolean = false,
    val profileImgUrl: String = "",
    val nickname: String = "",
    val followings: List<FollowingPreviewData> = emptyList(),
)
