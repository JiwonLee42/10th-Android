package com.example.and_practice

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    // 현재 선택된 감정
    private val _selectedEmotion = MutableStateFlow<Emotion?>(null)
    val selectedEmotion: StateFlow<Emotion?> = _selectedEmotion

    // 감정 선택 시 상태 업데이트
    fun selectedEmotion(emotion: Emotion){
        _selectedEmotion.value = emotion
    }
}