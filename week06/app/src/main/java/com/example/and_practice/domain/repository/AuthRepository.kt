package com.example.and_practice.domain.repository

import com.example.and_practice.data.remote.dto.ApiResponse
import com.example.and_practice.data.remote.dto.AuthTokenResponseDTO
import com.example.and_practice.data.remote.dto.LoginRequestDTO
import com.example.and_practice.data.remote.dto.RefreshTokenRequestDTO

interface AuthRepository {
    suspend fun login(): ApiResponse<AuthTokenResponseDTO> {
        return login(
            LoginRequestDTO(
                email = MOCK_DEV_EMAIL,
                password = MOCK_DEV_PASSWORD
            )
        )
    }

    suspend fun login(request: LoginRequestDTO): ApiResponse<AuthTokenResponseDTO>

    suspend fun refreshToken(request: RefreshTokenRequestDTO): ApiResponse<AuthTokenResponseDTO>

    fun logout()

    companion object {
        const val MOCK_DEV_EMAIL = "dev-user@project.local"
        const val MOCK_DEV_PASSWORD = "dev1234!"
    }
}
