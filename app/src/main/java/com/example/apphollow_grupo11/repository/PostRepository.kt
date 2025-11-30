package com.example.apphollow_grupo11.repository


import com.example.apphollow_grupo11.model.Post
import com.example.apphollow_grupo11.network.ApiService

open class PostRepository(
    private val apiService: ApiService
) {

    // Función real que luego testea con mocks
    open suspend fun getPosts(): List<Post> {
        val response = apiService.getPosts()   // Response<List<Post>>

        if (response.isSuccessful) {
            // body() puede ser null, por eso el ?: emptyList()
            return response.body() ?: emptyList()
        } else {
            // Lanza excepción o maneja el error como prefieras
            throw Exception("Error al obtener posts: ${response.code()}")
        }
    }
}