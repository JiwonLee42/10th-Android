package com.example.and_practice.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.and_practice.data.remote.api.toApiError
import com.example.and_practice.data.remote.dto.UpdateMyProfileRequestDTO
import com.example.and_practice.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    private val _eventState = MutableSharedFlow<ProfileEventState>()
    val eventState: SharedFlow<ProfileEventState> = _eventState.asSharedFlow()

    init {
        loadProfile()
    }

    fun loadProfile() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching { repository.getMyProfile() }
                .onSuccess { profile ->
                    val followings = profile.followings.map {
                        FollowingPreviewData(
                            id = it.userId,
                            imageUrl = it.profileImageUrl
                        )
                    }

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            profileImgUrl = profile.profileImageUrl,
                            nickname = profile.nickname,
                            followings = followings
                        )
                    }
                }
                .onFailure { throwable ->
                    _uiState.update { it.copy(isLoading = false) }
                    _eventState.emit(
                        ProfileEventState.ShowError(
                            throwable.toApiError().defaultMessage
                        )
                    )
                }
        }
    }

    fun updateNickname(nickname: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                repository.updateMyProfile(UpdateMyProfileRequestDTO(nickname = nickname))
            }
                .onSuccess {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            nickname = nickname
                        )
                    }
                    _eventState.emit(ProfileEventState.ProfileUpdated)
                }
                .onFailure { throwable ->
                    _uiState.update { it.copy(isLoading = false) }
                    _eventState.emit(
                        ProfileEventState.ShowError(
                            throwable.toApiError().defaultMessage
                        )
                    )
                }
        }
    }
}
