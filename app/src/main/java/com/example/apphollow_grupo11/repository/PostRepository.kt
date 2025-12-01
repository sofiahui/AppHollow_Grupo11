package com.example.apphollow_grupo11.repository


import com.example.apphollow_grupo11.model.Post
import com.example.apphollow_grupo11.network.ApiService
import com.example.apphollow_grupo11.network.RetrofitPost


class PostRepository(
    private val api: ApiService = RetrofitPost.api   // por defecto el real
) {

    suspend fun getPosts(): List<Post> {
        val response = api.getPosts()

        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            emptyList()
        }
    }
}
