package com.example.apphollow_grupo11

import com.example.apphollow_grupo11.model.Post
import com.example.apphollow_grupo11.network.ApiService
import com.example.apphollow_grupo11.repository.PostRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import retrofit2.Response

class TestablePostRepository(
    private val testApi: ApiService
) : PostRepository(testApi) {

    override suspend fun getPosts(): List<Post> {
        val response = testApi.getPosts()
        return response.body() ?: emptyList()
    }
}

class PostRepositoryTest : StringSpec({

    "getPosts() debe retornar una lista de posts simulada" {

        val fakePosts = listOf(
            Post(1, 1, "Título 1", "Cuerpo 1"),
            Post(2, 2, "Título 2", "Cuerpo 2")
        )

        val mockApi = mockk<ApiService>()
        coEvery { mockApi.getPosts() } returns Response.success(fakePosts)

        val repo = TestablePostRepository(testApi = mockApi)

        runTest {
            repo.getPosts() shouldContainExactly fakePosts
        }
    }
})
