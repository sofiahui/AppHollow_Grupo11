package com.example.apphollow_grupo11.network

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
