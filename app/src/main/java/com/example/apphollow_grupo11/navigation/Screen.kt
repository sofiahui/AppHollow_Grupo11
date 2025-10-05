package com.example.apphollow_grupo11.navigation

sealed class Screen (val route: String){
    object Home : Screen("Home")
    object Perfil : Screen ("Perfil")
}