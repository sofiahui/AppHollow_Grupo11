package com.example.apphollow_grupo11.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.apphollow_grupo11.data.LoginResponse
import com.example.apphollow_grupo11.ui.components.AppBottomBar
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.apphollow_grupo11.navigation.Screen
import com.example.apphollow_grupo11.viewmodel.PerfilViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(
    navController: NavController,
    viewModel: PerfilViewModel = viewModel(),
) {

    val usuario = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.get<LoginResponse>("usuarioLogeado")

    // Para evitar múltiples ejecuciones
    LaunchedEffect(usuario?.email) {
        usuario?.let {
            viewModel.cargarUsuario(
                nombre = it.name,
                correo = it.email,
                direccion = it.address
            )
        }
    }

    val estado by viewModel.estado.collectAsState()

    val items = listOf(Screen.Home, Screen.Perfil)
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    // val selectedIndex = items.indexOfFirst { it.route == currentRoute }

    Scaffold(
        bottomBar = {
            AppBottomBar(navController, usuario)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = estado.nombre,
                onValueChange = {},
                enabled = false,
                label = { Text("Nombre") }
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = estado.correo,
                onValueChange = {},
                enabled = false,
                label = { Text("Correo") }
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = estado.direccion,
                onValueChange = {},
                enabled = false,
                label = { Text("Dirección") }
            )
        }
    }
}
