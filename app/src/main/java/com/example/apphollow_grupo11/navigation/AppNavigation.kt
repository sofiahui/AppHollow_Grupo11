package com.example.apphollow_grupo11.navigation


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.apphollow_grupo11.ui.screen.HomeScreen
import com.example.apphollow_grupo11.ui.screen.PerfilScreen
import com.example.apphollow_grupo11.ui.screen.RegistroScreen
import com.example.apphollow_grupo11.viewmodels.MainViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    // Escucha los eventos emitidos por el ViewModel
    LaunchedEffect(Unit) {
        viewModel.navigationEvents.collectLatest { event ->
            when (event) {
                is NavigationEvent.NavigateTo -> {
                    navController.navigate(event.route.route) {
                        launchSingleTop = true
                        restoreState = true
                        event.popUpToRoute?.let {
                            popUpTo(it.route) { inclusive = event.inclusive }
                        }
                    }
                }
                NavigationEvent.PopBackStack -> navController.popBackStack()
                NavigationEvent.NavigateUp -> navController.navigateUp()
            }
        }
    }

    Scaffold { innerPadding ->
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
            composable(Screen.Registro.route) {
                RegistroScreen(navController = navController, viewModel = viewModel)
            }
        }
    }
}
