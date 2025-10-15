// MainActivity.kt
// Actividad principal de la aplicación. Aquí se inicializa la interfaz y la navegación principal usando Jetpack Compose y Material3.
// Es el punto de entrada de la app de venta de ropa.
package com.example.apphollow_grupo11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apphollow_grupo11.navigation.NavigationEvent
import com.example.apphollow_grupo11.navigation.Screen
import com.example.apphollow_grupo11.ui.screen.HomeScreen
import com.example.apphollow_grupo11.ui.screen.PerfilScreen
import com.example.apphollow_grupo11.ui.theme.AppHollow_Grupo11Theme
import com.example.apphollow_grupo11.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.activity.enableEdgeToEdge


@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppHollow_Grupo11Theme {
                val viewModel: MainViewModel = viewModel()
                val navController = rememberNavController()

                // 🔹 Escucha los eventos de navegación del ViewModel
                LaunchedEffect(Unit) {
                    viewModel.navigationEvents.collectLatest { event ->
                        when (event) {
                            is NavigationEvent.NavigateTo -> {
                                navController.navigate(event.route.route) {
                                    // Evita duplicados en la pila de navegación
                                    launchSingleTop = true
                                    restoreState = true
                                    event.popUpToRoute?.let {
                                        popUpTo(it.route) {
                                            inclusive = event.inclusive
                                        }
                                    }
                                }
                            }

                            is NavigationEvent.PopBackStack -> navController.popBackStack()
                            is NavigationEvent.NavigateUp -> navController.navigateUp()
                        }
                    }
                }

                // 🔹 Estructura visual base con Scaffold + NavHost
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Home.route) {
                            HomeScreen(navController = navController, viewModel = viewModel)
                        }
                        composable(Screen.Perfil.route) {
                            PerfilScreen(navController = navController, viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppHollow_Grupo11Theme {
        Greeting("Android")
    }
}