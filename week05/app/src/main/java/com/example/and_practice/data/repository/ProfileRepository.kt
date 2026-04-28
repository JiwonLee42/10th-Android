package com.example.and_practice.data.repository

import com.example.and_practice.data.entity.ProfileEntity
import com.example.and_practice.data.remote.api.ProfileApi
import com.example.and_practice.data.remote.api.ApiClient
import com.example.and_practice.data.remote.dto.ApiResponse
import com.example.and_practice.data.remote.dto.MyFollowingsResponseDTO
import com.example.and_practice.data.remote.dto.UpdateMyProfileRequestDTO
import com.example.and_practice.data.remote.dto.getOrThrow

class ProfileRepository(
    private val profileApi: ProfileApi = ApiClient.profileApi
) {

    suspend fun getMyProfile(): ProfileEntity {
        val profile = profileApi.getMyProfile().getOrThrow()

        return ProfileEntity(
            id = 1L,
            nickName = profile.nickname,
            profileImageUrl = profile.profileImageUrl
        )
    }

    suspend fun getMyFollowings(): ApiResponse<MyFollowingsResponseDTO> {
        return profileApi.getMyFollowings()
    }

    suspend fun updateMyProfile(request: UpdateMyProfileRequestDTO): ApiResponse<Unit> {
        return profileApi.updateProfile(request)
    }
}
