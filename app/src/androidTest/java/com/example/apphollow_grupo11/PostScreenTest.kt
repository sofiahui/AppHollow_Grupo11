package com.example.apphollow_grupo11

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.apphollow_grupo11.model.Post
import com.example.apphollow_grupo11.ui.screen.PostScreen
import com.example.apphollow_grupo11.ui.theme.AppHollow_Grupo11Theme
import com.example.apphollow_grupo11.viewmodel.PostViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun postScreen_muestraListado() {
        // Simulamos los datos que el ViewModel entregaría
        val fakePosts = listOf(
            Post(userId = 1, id = 1, title = "Título 1", body = "Contenido 1"),
            Post(userId = 2, id = 2, title = "Título 2", body = "Contenido 2")
        )

        val fakeViewModel = object : PostViewModel() {
            override fun fetchPosts() {
                _postList.value = fakePosts
            }
        }
        composeRule.setContent {
            AppHollow_Grupo11Theme {
                PostScreen(viewModel = fakeViewModel)
            }

        }


        // Renderizamos el PostScreen con el ViewModel falso

        // Validamos que los títulos se muestren correctamente en la UI
        composeRule.onNodeWithText("Título 1").assertIsDisplayed()
        composeRule.onNodeWithText("Título 2").assertIsDisplayed()
    }
}