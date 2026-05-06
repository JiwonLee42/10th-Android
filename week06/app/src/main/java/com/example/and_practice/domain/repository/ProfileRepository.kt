package com.example.and_practice.domain.repository

import com.example.and_practice.data.remote.dto.MeProfileResponseDTO
import com.example.and_practice.data.remote.dto.UpdateMyProfileRequestDTO

interface ProfileRepository {
    suspend fun getMyProfile(): MeProfileResponseDTO

    suspend fun updateMyProfile(request: UpdateMyProfileRequestDTO)
}
