package com.example.apphollow_grupo11.network


import com.example.apphollow_grupo11.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Path

interface ApiService {

    // Obtener todos los posts
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    // Crear un post nuevo (si tu API lo soporta)
    @POST("posts")
    suspend fun crearPost(
        @Body post: Post
    ): Response<Post>

    // Obtener un post por ID (opcional)
    @GET("posts/{id}")
    suspend fun getPostPorId(
        @Path("id") id: Int
    ): Response<Post>
}
