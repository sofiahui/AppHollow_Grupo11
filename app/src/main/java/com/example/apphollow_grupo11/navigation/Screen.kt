package com.example.apphollow_grupo11.navigation


sealed class Screen (
    val route: String,
    val title: String,
) {
    object Home : Screen("Home", "Inicio")
    object Login : Screen("Login", "Login")
    object Registro : Screen("Registro", "Registro")
    object Resumen : Screen("Resumen", "Resumen")
    object Perfil : Screen("Perfil", "Perfil")
    object Admin : Screen("Admin", "Admin")

    object EditUser : Screen("EditUser", "Editar Usuario")

}