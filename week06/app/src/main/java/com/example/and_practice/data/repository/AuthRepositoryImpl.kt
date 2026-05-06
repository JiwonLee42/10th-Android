package com.example.and_practice.data.repository

import com.example.and_practice.data.remote.TokenStorage
import com.example.and_practice.data.remote.api.ApiClient
import com.example.and_practice.data.remote.api.AuthApi
import com.example.and_practice.data.remote.dto.ApiResponse
import com.example.and_practice.data.remote.dto.AuthTokenResponseDTO
import com.example.and_practice.data.remote.dto.LoginRequestDTO
import com.example.and_practice.data.remote.dto.RefreshTokenRequestDTO
import com.example.and_practice.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authApi: AuthApi = ApiClient.authApi
) : AuthRepository {

    override suspend fun login(request: LoginRequestDTO): ApiResponse<AuthTokenResponseDTO> {
        val response = authApi.devLogin(request)
        response.result?.let { token ->
            TokenStorage.saveTokens(token.accessToken, token.refreshToken)
        }
        return response
    }

    override suspend fun refreshToken(request: RefreshTokenRequestDTO): ApiResponse<AuthTokenResponseDTO> {
        val response = authApi.refresh(request)
        response.result?.let { token ->
            TokenStorage.saveTokens(token.accessToken, token.refreshToken)
        }
        return response
    }

    override fun logout() {
        TokenStorage.clear()
    }
}
