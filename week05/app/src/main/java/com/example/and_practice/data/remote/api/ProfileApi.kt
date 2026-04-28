package com.example.and_practice.data.remote.api

import com.example.and_practice.data.remote.dto.ApiResponse
import com.example.and_practice.data.remote.dto.MeProfileResponseDTO
import com.example.and_practice.data.remote.dto.MyFollowingsResponseDTO
import com.example.and_practice.data.remote.dto.UpdateMyProfileRequestDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface ProfileApi {

    @GET("api/v1/me/profile")
    suspend fun getMyProfile(): ApiResponse<MeProfileResponseDTO>

    @GET("api/v1/me/profile/followings")
    suspend fun getMyFollowings(): ApiResponse<MyFollowingsResponseDTO>

    @PATCH("api/v1/me/profile")
    suspend fun updateProfile(
        @Body request: UpdateMyProfileRequestDTO
    ): ApiResponse<Unit>
}
