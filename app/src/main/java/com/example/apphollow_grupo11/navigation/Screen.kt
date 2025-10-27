package com.example.apphollow_grupo11.navigation

sealed class Screen (val route: String){
    object Home : Screen("Home")
    object Login : Screen ("Login")
    object Registro : Screen ("Registro")

    object Resumen : Screen (route = "Resumen")

    object Perfil : Screen (route = "Perfil")
}