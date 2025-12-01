package com.example.apphollow_grupo11

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import com.example.apphollow_grupo11.model.Post
import com.example.apphollow_grupo11.ui.screen.PostScreen
import com.example.apphollow_grupo11.ui.theme.AppHollow_Grupo11Theme
import com.example.apphollow_grupo11.viewmodel.FakePostViewModel

class PostScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun postScreen_muestraListado() {
        val fakePosts = listOf(
            Post(userId = 1, id = 1, title = "Título 1", body = "Contenido 1"),
            Post(userId = 2, id = 2, title = "Título 2", body = "Contenido 2")
        )

        val fakeViewModel = FakePostViewModel()
        fakeViewModel.setFakePosts(fakePosts)

        composeRule.setContent {
            AppHollow_Grupo11Theme {
                PostScreen(
                    viewModel = fakeViewModel,
                    autoFetch = false
                )
            }
        }

        composeRule.onNodeWithText("Título 1").assertIsDisplayed()
        composeRule.onNodeWithText("Título 2").assertIsDisplayed()
    }
}


