package com.example.apphollow_grupo11



import com.example.apphollow_grupo11.model.Post
import com.example.apphollow_grupo11.viewmodel.PostViewModel
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class PostViewModelTest : StringSpec( body = {
    // *postList debe contener los datos esperados después de fetchPosts()*
    // Creamos una subclase falsa de PostViewModel que sobrescribe el repositorio
    val fakePosts = listOf(
        Post(userId = 1, id = 1, title = "Titulo 1", body = "Contenido 1"),
        Post(userId = 2, id = 2, title = "Titulo 2", body = "Contenido 2")
    )

    val testViewModel = object : PostViewModel() {
        // Usage
        override fun fetchPost() {
            _postList.value = fakePosts
        }
    }

    runTest {
        testViewModel.fetchPosts()
        testViewModel.postList.value shouldContainExactly fakePosts
    }
})
