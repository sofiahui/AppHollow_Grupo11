package com.example.apphollow_grupo11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.apphollow_grupo11.navigation.AppNavigation
import com.example.apphollow_grupo11.ui.components.NavigationDrawerScreen
import com.example.apphollow_grupo11.ui.theme.AppHollow_Grupo11Theme
import com.example.apphollow_grupo11.viewmodel.MainViewModel
import com.example.apphollow_grupo11.viewmodel.PostViewModel

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppHollow_Grupo11Theme {
                //  Se crea el controlador de navegación
                val navController = rememberNavController()
                //  Se obtiene el ViewModel
                val mainViewModel: MainViewModel = viewModel()

                //Post ViewModel para post
                val postViewModel: PostViewModel = viewModel ()

                // Se pasa correctamente al Drawer y al sistema de navegación
                NavigationDrawerScreen(navController = navController) {
                    AppNavigation(navController = navController, viewModel = mainViewModel,postViewModel = postViewModel)
                }
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
        val postViewModel: PostViewModel = viewModel ()
        AppNavigation(navController = navController, viewModel = mainViewModel, postViewModel = postViewModel)
    }
}
