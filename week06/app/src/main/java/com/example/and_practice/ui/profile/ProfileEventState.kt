package com.example.and_practice.ui.profile

sealed class ProfileEventState {
    data class ShowError(val message: String) : ProfileEventState()
    data object ProfileUpdated : ProfileEventState()
}
