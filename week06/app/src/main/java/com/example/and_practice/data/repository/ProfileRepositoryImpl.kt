package com.example.and_practice.data.repository

import com.example.and_practice.data.remote.api.ProfileApi
import com.example.and_practice.data.remote.dto.MeProfileResponseDTO
import com.example.and_practice.data.remote.dto.UpdateMyProfileRequestDTO
import com.example.and_practice.data.remote.dto.getOrThrow
import com.example.and_practice.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val profileApi: ProfileApi
) : ProfileRepository {

    override suspend fun getMyProfile(): MeProfileResponseDTO {
        return profileApi.getMyProfile().getOrThrow()
    }

    override suspend fun updateMyProfile(request: UpdateMyProfileRequestDTO) {
        profileApi.updateProfile(request).getOrThrow()
    }
}
