package com.example.and_practice.data.repository

import com.example.and_practice.data.remote.TokenStorage
import com.example.and_practice.data.remote.api.ApiClient
import com.example.and_practice.data.remote.api.AuthApi
import com.example.and_practice.data.remote.dto.ApiResponse
import com.example.and_practice.data.remote.dto.AuthTokenResponseDTO
import com.example.and_practice.data.remote.dto.LoginRequestDTO
import com.example.and_practice.data.remote.dto.RefreshTokenRequestDTO

class AuthRepository(
    private val authApi: AuthApi = ApiClient.authApi
) {

    // 서버에서 Mock으로 넣어둔 계정(로그인이 없으니 하드코딩..)
    companion object {
        private const val MOCK_DEV_EMAIL = "dev-user@project.local"
        private const val MOCK_DEV_PASSWORD = "dev1234!"
    }

    suspend fun login(): ApiResponse<AuthTokenResponseDTO> {
        return login(
            LoginRequestDTO(
                email = MOCK_DEV_EMAIL,
                password = MOCK_DEV_PASSWORD
            )
        )
    }

    suspend fun login(request: LoginRequestDTO): ApiResponse<AuthTokenResponseDTO> {
        val response = authApi.devLogin(request)
        response.result?.let { token ->
            TokenStorage.saveTokens(token.accessToken, token.refreshToken)
        }
        return response
    }

    suspend fun refreshToken(request: RefreshTokenRequestDTO): ApiResponse<AuthTokenResponseDTO> {
        val response = authApi.refresh(request)
        response.result?.let { token ->
            TokenStorage.saveTokens(token.accessToken, token.refreshToken)
        }
        return response
    }

    fun logout() {
        TokenStorage.clear()
    }
}
