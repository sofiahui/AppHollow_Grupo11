package com.example.apphollow_grupo11.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.collectAsState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.apphollow_grupo11.ui.components.AppBottomBar
import com.example.apphollow_grupo11.viewmodel.AdminUserViewModel
import com.example.apphollow_grupo11.viewmodel.LoginViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.apphollow_grupo11.data.LoginResponse
import com.example.apphollow_grupo11.data.UserResponse
import com.example.apphollow_grupo11.navigation.Screen



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminUserScreen(
    navController: NavController,
    viewModel: AdminUserViewModel = viewModel(),
    loginViewModel: LoginViewModel = viewModel()
) {
    val usuarios by viewModel.listaUsuarios.collectAsState()

    val usuario = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.get<LoginResponse>("usuarioLogeado")

    // Estado para controlar el diálogo de confirmación de eliminación
    val userToDelete = remember { mutableStateOf<UserResponse?>(null) }

    LaunchedEffect(Unit) {
        viewModel.obtenerUsuarios()
    }

    // Dialogo de confirmación
    userToDelete.value?.let { candidate ->
        AlertDialog(
            onDismissRequest = { userToDelete.value = null },
            title = { Text("Atención") },
            text = { Text("Esto desactivará al usuario \"${candidate.name}\". ¿Deseas continuar?") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.desactivarUsuario(candidate.id)
                    userToDelete.value = null
                }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { userToDelete.value = null }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Administración de Usuarios") }) },
        bottomBar = {
            AppBottomBar(navController, usuario)
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {

            Button(
                onClick = { /* abrir registro */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Crear usuario")
            }

            LazyColumn {
                items(usuarios) { user ->
                    ListItem(
                        headlineContent = { Text(user.name) },
                        supportingContent = { Text(user.email) },
                        trailingContent = {
                            Row {
                                TextButton(onClick = {
                                    navController.currentBackStackEntry
                                        ?.savedStateHandle
                                        ?.set("userToEdit", user)

                                    navController.navigate(Screen.EditUser.route)
                                }) { Text("Editar") }

                                TextButton(onClick = {
                                    userToDelete.value = user
                                }) { Text("Borrar") }
                            }
                        }
                    )
                }
            }
        }
    }
}
