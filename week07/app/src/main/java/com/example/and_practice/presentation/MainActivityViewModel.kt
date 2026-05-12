package com.example.and_practice.presentation

import com.example.and_practice.base.BaseViewModel
import com.example.and_practice.base.UiEvent
import com.example.and_practice.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class MainActivityUiState(
    val isReady: Boolean = true
) : UiState

sealed interface MainActivityEvent : UiEvent

@HiltViewModel
class MainActivityViewModel @Inject constructor() : BaseViewModel<MainActivityUiState, MainActivityEvent>(
    initialState = MainActivityUiState()
)
