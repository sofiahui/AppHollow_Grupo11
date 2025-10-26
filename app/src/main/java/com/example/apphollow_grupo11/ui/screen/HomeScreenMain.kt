package com.example.apphollow_grupo11.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apphollow_grupo11.ui.utils.obtenerWindowSizeClass
import com.example.apphollow_grupo11.viewmodel.MainViewModel
import com.example.apphollow_grupo11.ui.components.NavigationDrawerScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
	navController: NavController,
	viewModel: MainViewModel = viewModel()
) {
	NavigationDrawerScreen(navController = navController) { openDrawer ->

		val windowSizeClass = obtenerWindowSizeClass()

		Scaffold(
			topBar = {
				TopAppBar(
					title = { Text("Sleepy Hollow Store") },
					navigationIcon = {
						IconButton(onClick = openDrawer) {
							Icon(
								imageVector = Icons.Default.Menu,
								contentDescription = "Abrir menú"
							)
						}
					}
				)
			}
		) { innerPadding ->
			Column(
				modifier = Modifier
					.fillMaxSize()
					.padding(innerPadding)
					.padding(15.dp),
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center
			) {
				when (windowSizeClass.widthSizeClass) {
					WindowWidthSizeClass.Compact -> HomeScreenCompacta(navController)
					WindowWidthSizeClass.Medium -> HomeScreenMediana()
					WindowWidthSizeClass.Expanded -> HomeScreenExtendida()
				}
			}
		}
	}
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHomeScreen() {
	val fakeNavController = rememberNavController()
	HomeScreen(navController = fakeNavController)
}
