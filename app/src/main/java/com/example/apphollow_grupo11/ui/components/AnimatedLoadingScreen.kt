package com.example.apphollow_grupo11.ui.components


import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.apphollow_grupo11.navigation.UiState

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedLoadingScreen(
    state: UiState,
    onStateChange: (UiState) -> Unit
) {
    AnimatedContent(
        targetState = state,
        transitionSpec = {
            fadeIn(animationSpec = tween(5000)) togetherWith
                    fadeOut(animationSpec = tween(5000))
        },
        label = "Animated Content",
        modifier = Modifier.fillMaxSize()
    ) { targetState ->
        when (targetState) {
            UiState.Loading -> LoadingScreen()
            UiState.Loaded -> LoadedScreen()
            UiState.Error -> ErrorScreen()
        }
    }
}
