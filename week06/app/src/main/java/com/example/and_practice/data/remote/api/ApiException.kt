package com.example.and_practice.data.remote.api

object ErrorCode {
    const val INVALID_REQUEST = "COMMON400_1"
    const val INVALID_FORMAT = "COMMON400_2"
    const val INVALID_DATE = "COMMON400_3"

    const val UNAUTHORIZED = "COMMON401_1"

    const val FORBIDDEN = "COMMON403_1"

    const val NOT_FOUND = "COMMON404_1"

    const val DUPLICATE_RESOURCE = "COMMON409_1"
    const val CONCURRENT_MODIFICATION = "COMMON409_2"

    const val INTERNAL_SERVER_ERROR = "COMMON500_1"
}

class ApiException(
    val errorCode: String,
    override val message: String
) : RuntimeException(message)
