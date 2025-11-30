package com.example.apphollow_grupo11

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.apphollow_grupo11.model.Post
import com.example.apphollow_grupo11.ui.screen.PostScreen
import com.example.apphollow_grupo11.viewmodel.PostViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class PostScreenTest {

    text
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun el_titulo_de_post_debe_aparecer_en_pantalla() {
        // Simulamos los datos que el ViewModel entregaría
        val fakePosts = listOf(
            Post(userId = 1, id = 1, title = "Título 1", body = "Contenido 1"),
            Post(userId = 2, id = 2, title = "Título 2", body = "Contenido 2")
        )

        // Subclase falsa de PostViewModel con StateFlow simulado
        val fakeViewModel = object : PostViewModel() {
            override val postList = MutableStateFlow(value = fakePosts)
        }

        // Renderizamos el PostScreen con el ViewModel falso
        composeRule.setContent {
            PostScreen(viewModel = fakeViewModel)
        }

        // Validamos que los títulos se muestren correctamente en la UI
        composeRule.onNodeWithText(text = "Título 1").assertIsDisplayed()
        composeRule.onNodeWithText(text = "Título 2").assertIsDisplayed()
    }
}