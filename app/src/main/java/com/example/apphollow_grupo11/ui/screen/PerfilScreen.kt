package com.example.apphollow_grupo11.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.apphollow_grupo11.navigation.Screen
import androidx.compose.ui.tooling.preview.Preview
import com.example.apphollow_grupo11.viewmodel.PerfilViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(
    navController: NavController,
    viewModel: PerfilViewModel = viewModel()
) {
    // Evitar NPE leyendo estado desde ViewModel con values por defecto
    val estado by remember { derivedStateOf { /* si tu ViewModel tiene estado, úsalo aquí */ null } }

    // Lista de pantallas disponibles en la barra de navegación
    val items = listOf(Screen.Home, Screen.Perfil)
    var selectedItem by remember { mutableStateOf(1) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            navController.navigate(screen.route)
                        },
                        label = { Text(text = screen.route) },
                        icon = {
                            Icon(
                                imageVector = if (screen == Screen.Home) Icons.Default.Home else Icons.Default.Person,
                                contentDescription = screen.route
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¡Bienvenido al Perfil!",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

/*@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPerfilScreen() {
    val fakeNavController = androidx.navigation.compose.rememberNavController()

    // No usamos el sistema de ViewModel aquí, solo un objeto simple
   // val fakeViewModel =

    PerfilScreen(navController = fakeNavController, viewModel = fakeViewModel)}*/