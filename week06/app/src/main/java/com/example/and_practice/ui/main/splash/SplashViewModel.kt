package com.example.and_practice.ui.main.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.and_practice.data.remote.api.toApiError
import com.example.and_practice.domain.repository.AuthRepository
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
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    private val _eventState = MutableSharedFlow<SplashEventState>()
    val eventState: SharedFlow<SplashEventState> = _eventState.asSharedFlow()

    init {
        login()
    }

    private fun login() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                authRepository.login()
                _eventState.emit(SplashEventState.NavigateToMain)
            } catch (throwable: Throwable) {
                _eventState.emit(
                    SplashEventState.ShowError(
                        throwable.toApiError().defaultMessage
                    )
                )
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}
