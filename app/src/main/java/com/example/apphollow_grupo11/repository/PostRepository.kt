package com.example.apphollow_grupo11.repository


import com.example.apphollow_grupo11.model.Post
import com.example.apphollow_grupo11.network.RetrofitPost


class PostRepository {

    suspend fun getPosts(): List<Post> {
        val response = RetrofitPost.api.getPosts()

        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            emptyList()
        }
    }
}