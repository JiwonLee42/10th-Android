package com.example.and_practice.data.remote.api

import com.example.and_practice.data.remote.dto.ApiResponse
import com.example.and_practice.data.remote.dto.AuthTokenResponseDTO
import com.example.and_practice.data.remote.dto.LoginRequestDTO
import com.example.and_practice.data.remote.dto.RefreshTokenRequestDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("api/v1/auth/dev-login")
    suspend fun devLogin(
        @Body request: LoginRequestDTO
    ): ApiResponse<AuthTokenResponseDTO>

    @POST("api/v1/auth/refresh")
    suspend fun refresh(
        @Body request: RefreshTokenRequestDTO
    ): ApiResponse<AuthTokenResponseDTO>
}
