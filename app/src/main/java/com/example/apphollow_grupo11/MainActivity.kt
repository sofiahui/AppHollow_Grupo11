// MainActivity.kt
// Actividad principal de la aplicación. Aquí se inicializa la interfaz y la navegación principal usando Jetpack Compose y Material3.
// Es el punto de entrada de la app de venta de ropa.
package com.example.apphollow_grupo11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.compose.rememberNavController

import com.example.apphollow_grupo11.ui.theme.AppHollow_Grupo11Theme
import com.example.apphollow_grupo11.viewmodel.MainViewModel
import com.example.apphollow_grupo11.navigation.AppNavigation
import androidx.compose.ui.tooling.preview.Preview
import androidx.activity.enableEdgeToEdge


@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppHollow_Grupo11Theme {
                // Crear el navController y el ViewModel
                val navController = rememberNavController()
                val mainViewModel: MainViewModel = viewModel()

                // 👉 Llamamos directamente a AppNavigation (la navegación central del proyecto)
                AppNavigation(
                    navController = navController,
                    viewModel = mainViewModel
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    AppHollow_Grupo11Theme {
        val navController = rememberNavController()
        val mainViewModel: MainViewModel = viewModel()
        AppNavigation(navController = navController, viewModel = mainViewModel)
    }
}


