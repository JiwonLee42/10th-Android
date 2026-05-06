package com.example.and_practice.data.remote.dto

import com.example.and_practice.data.remote.api.ApiException

data class ApiResponse<T>(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: T? = null,
    val traceId: String? = null
)

fun <T> ApiResponse<T>.getOrThrow(): T {
    if (!isSuccess) throw ApiException(code, message)
    return result ?: throw ApiException(code, "result is null")
}

// api 호출 포함하므로 suspend로
suspend fun <T> handleApiResponse(block: suspend () -> ApiResponse<T>): Result<T> {
    return runCatching { block().getOrThrow() }
}
