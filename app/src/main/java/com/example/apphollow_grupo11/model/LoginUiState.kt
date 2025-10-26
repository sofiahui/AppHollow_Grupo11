package com.example.apphollow_grupo11.model

data class LoginUiState(
    val usuario: String = "",
    val clave: String = "",
    val errores: LoginErrores = LoginErrores()
)
