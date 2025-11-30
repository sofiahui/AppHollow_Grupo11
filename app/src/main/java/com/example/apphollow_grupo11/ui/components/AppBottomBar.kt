package com.example.apphollow_grupo11.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.apphollow_grupo11.R
import com.example.apphollow_grupo11.navigation.Screen
import com.example.apphollow_grupo11.data.LoginResponse

@Composable
fun AppBottomBar(
    navController: NavController,
    usuario: LoginResponse?
) {
    val items = if (usuario?.admin == true) {
        listOf(Screen.Perfil, Screen.Admin)
    } else {
        listOf(Screen.Perfil)
    }

    val currentRoute = navController.currentBackStackEntry?.destination?.route
    val selectedIndex = items.indexOfFirst { it.route == currentRoute }

    NavigationBar {

        items.forEachIndexed { index, screen ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("usuarioLogeado", usuario)

                    navController.navigate(screen.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painterResource(
                            id = when (screen) {
                                Screen.Perfil -> R.drawable.ic_profile
                                Screen.Admin -> R.drawable.ic_admin
                                else -> R.drawable.ic_profile // fallback
                            }
                        ),
                        contentDescription = screen.title
                    )
                },
                label = { Text(screen.title) }
            )
        }
    }
}
