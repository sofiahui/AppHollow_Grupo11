
package com.example.apphollow_grupo11.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.apphollow_grupo11.navigation.UiState
import com.example.apphollow_grupo11.ui.components.AnimatedLoadingScreen
import com.example.apphollow_grupo11.viewmodel.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.unit.dp
import com.example.apphollow_grupo11.R
import com.example.apphollow_grupo11.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel()
) {
    var uiState by remember { mutableStateOf<UiState>(UiState.Loaded) }
    val estado by viewModel.estado.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Bienvenido a Sleepy Hollow") }
            )
        }
    ) { innerPadding ->

        when (uiState) {
            UiState.Loading -> {
                AnimatedLoadingScreen(
                    state = uiState,
                    onStateChange = { uiState = it }
                )
            }

            else -> {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // 🖼️ Logo de la app
                    Image(
                        painter = painterResource(id = R.drawable.logo_tienda),
                        contentDescription = "Logo de la app",
                        modifier = Modifier
                            .fillMaxWidth(0.6f) // ocupa el 60% del ancho
                            .height(160.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.height(24.dp))

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
                            uiState = UiState.Loading
                            coroutineScope.launch {
                                // Asegurar un tiempo mínimo de carga para mejor UX
                                val minLoadingMs = 2000L // 2 segundo de carga mínima
                                val start = System.currentTimeMillis()

                                val resultado = viewModel.login()

                                // Calcular tiempo transcurrido
                                val elapsed = System.currentTimeMillis() - start

                                // Si fue menos que el mínimo, esperar el tiempo restante
                                // El login es super rapido con la base de datos local
                                // por eso puse esta función para que por lo menos se vea un poquito la animacion
                                if (elapsed < minLoadingMs) {
                                    delay(minLoadingMs - elapsed)
                                }

                                uiState = UiState.Loaded

                                if (resultado) {
                                    val usuario = viewModel.usuarioLogeado.value

                                    val destino = if (usuario?.admin == true) {
                                        Screen.Admin.route
                                    } else {
                                        Screen.Perfil.route
                                    }

                                    navController.navigate(destino) {
                                        popUpTo(Screen.Login.route) { inclusive = true }
                                    }

                                    navController.currentBackStackEntry
                                        ?.savedStateHandle
                                        ?.set("usuarioLogeado", usuario)
                                } else {
                                    uiState = UiState.Error
                                    delay(4000)
                                    uiState = UiState.Loaded
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Ingresar")
                    }


                    if (uiState == UiState.Error) {
                        Text(
                            text = "Usuario o contraseña incorrectos",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }


                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { navController.navigate(Screen.Home.route) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Volver al inicio")
                    }

                    Spacer(modifier = Modifier.height(16.dp))


                    Text(
                        text = "¿Olvidaste tu contraseña?",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .clickable { /* Navegar a recuperar contraseña */ }
                    )


                }
            }
        }
    }
}

