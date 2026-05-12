package com.example.and_practice.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
// BaseViewModel 임시 설정
// Event, State로 분리
abstract class BaseViewModel<STATE : UiState, EVENT : UiEvent>(
    initialState: STATE
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<STATE> = _uiState.asStateFlow()

    private val _event = Channel<EVENT>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    protected fun updateState(reducer: (STATE) -> STATE) {
        _uiState.update(reducer)
    }

    protected suspend fun sendEvent(event: EVENT) {
        _event.send(event)
    }
}
