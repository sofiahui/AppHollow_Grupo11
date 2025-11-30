package com.example.apphollow_grupo11.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apphollow_grupo11.data.UserResponse
import com.example.apphollow_grupo11.viewmodel.AdminUserViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUserScreen(
    navController: NavController,
    viewModel: AdminUserViewModel,
    user: UserResponse
) {
    // Estado local para editar los campos
    var name by remember { mutableStateOf(user.name) }
    var email by remember { mutableStateOf(user.email) }
    var admin by remember { mutableStateOf(user.admin) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Usuario") }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = admin,
                    onCheckedChange = { admin = it }
                )
                Text("Administrador")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    // Crear objeto actualizado
                    val actualizado = user.copy(
                        name = name,
                        email = email,
                        admin = admin
                    )

                    // Llamar función del ViewModel
                    viewModel.editarUsuario(user.id, actualizado)

                    viewModel.obtenerUsuarios()

                    // Volver atrás
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar cambios")
            }
        }
    }
}
