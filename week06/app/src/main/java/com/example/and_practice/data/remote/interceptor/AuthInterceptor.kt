package com.example.and_practice.data.remote.interceptor

import com.example.and_practice.data.remote.TokenStorage
import okhttp3.Interceptor
import okhttp3.Response

// 인증이 필요한 API에 모두 헤더에 AccessToken을 보내도록 하는 인터셉터
class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val path = originalRequest.url.encodedPath
        val accessToken = TokenStorage.getAccessToken()

        if (
            accessToken.isNullOrBlank() ||
            path == "/api/v1/auth/dev-login" ||
            path == "/api/v1/auth/refresh"
        ) {
            return chain.proceed(originalRequest)
        }

        val authenticatedRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()

        return chain.proceed(authenticatedRequest)
    }
}
