
// Pantalla para mostrar y editar la información del perfil del usuario.
// Aquí se implementa la UI para ver y modificar datos personales, dirección y preferencias.
package com.example.apphollow_grupo11.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.apphollow_grupo11.navigation.Screen
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apphollow_grupo11.navigation.UiState
import com.example.apphollow_grupo11.ui.components.AnimatedLoadingScreen
import com.example.apphollow_grupo11.viewmodel.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {
    var uiState by remember { mutableStateOf<UiState>(UiState.Loaded) }
    val estado by viewModel.estado.collectAsState()
    val coroutineScope = rememberCoroutineScope() //

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Bienvenido a Sleepy Hollow") }
            )
        }
    ) { innerPadding ->   // 👈 te faltaban estas llaves
        if (uiState == UiState.Loading) {
            // 🔮 Mostrar animación de carga
            AnimatedLoadingScreen(
                state = uiState,
                onStateChange = { uiState = it }
            )
        } else {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Inicia sesión para continuar",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = estado.usuario,
                    onValueChange = viewModel::onUsuarioChange,
                    label = { Text("Usuario") },
                    isError = estado.errores.usuario != null,
                    supportingText = {
                        estado.errores.usuario?.let {
                            Text(it, color = MaterialTheme.colorScheme.error)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = estado.clave,
                    onValueChange = viewModel::onClaveChange,
                    label = { Text("Contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    isError = estado.errores.clave != null,
                    supportingText = {
                        estado.errores.clave?.let {
                            Text(it, color = MaterialTheme.colorScheme.error)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        // Cambiamos a "Loading" primero
                        uiState = UiState.Loading
                        // Simular proceso (como consulta a BD o login real)
                        coroutineScope.launch {
                            delay(2000)
                            if (viewModel.validar()) {
                                uiState = UiState.Loaded
                                navController.navigate("home")
                            } else {
                                uiState = UiState.Error
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ingresar")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "¿Olvidaste tu contraseña?",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable {
                            // aquí podrías navegar a recuperar contraseña
                            // navController.navigate("recuperar")
                        }
                )
            }
        }
    }
}
