package com.example.apphollow_grupo11.network

import com.example.apphollow_grupo11.data.LoginRequest
import com.example.apphollow_grupo11.data.LoginResponse
import com.example.apphollow_grupo11.data.UserRequest
import com.example.apphollow_grupo11.data.UserResponse
import retrofit2.Response
import retrofit2.http.PUT
import retrofit2.http.GET
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    // Registro de usuario (Create)
    @POST("api/users/register")
    suspend fun registrarUsuario(
        @Body user: UserRequest
    ): Response<UserResponse>

    // Login de usuario (Read)
    @POST("api/users/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("api/users")
    suspend fun obtenerUsuarios(): Response<List<UserResponse>>

    @PUT("api/users/{id}")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body user: UserResponse
    ): Response<UserResponse>

    // Obtener usuario por ID (Read)
    @GET("api/users/{id}")
    suspend fun obtenerUsuario(@Path("id") id: Int): Response<UserResponse>


}
