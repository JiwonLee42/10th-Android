package com.example.and_practice.data.remote.api

sealed class ApiError {
    abstract val defaultMessage: String

    data class BadRequest(override val defaultMessage: String = "입력값을 다시 확인해주세요.") : ApiError()
    data class Unauthorized(override val defaultMessage: String = "로그인이 필요합니다.") : ApiError()
    data class Forbidden(override val defaultMessage: String = "이 기능에 접근할 수 없습니다.") : ApiError()
    data class NotFound(override val defaultMessage: String = "요청한 데이터를 찾을 수 없습니다.") : ApiError()
    data class Conflict(override val defaultMessage: String = "이미 존재하는 데이터입니다.") : ApiError()
    data class ServerError(override val defaultMessage: String = "서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요.") : ApiError()
    object NetworkError : ApiError() {
        override val defaultMessage = "인터넷 연결을 확인해주세요."
    }
    data class Unknown(override val defaultMessage: String = "문제가 발생했습니다. 다시 시도해주세요.") : ApiError()
}
