package com.example.apphollow_grupo11.navigation

sealed class UiState{
    object Loading : UiState()
    object Loaded : UiState()
    object Error : UiState()
}
