
// HomeScreen.kt
// Pantalla principal de la app de venta de ropa. Aquí se muestran elementos visuales básicos y se aplican buenas prácticas de Jetpack Compose y Material3.
package com.example.apphollow_grupo11.ui.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
	navController: NavController,
	viewModel: MainViewModel = viewModel()
) {
	val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
	val scope = rememberCoroutineScope()

	// Envolvemos todo dentro del drawer
	ModalNavigationDrawer(
		drawerState = drawerState,
		drawerContent = {
			ModalDrawerSheet {
				Text(
					text = "Menú",
					modifier = Modifier.padding(16.dp),
					style = MaterialTheme.typography.titleMedium
				)

				NavigationDrawerItem(
					label = { Text("Ir a Perfil") },
					selected = false,
					onClick = {
						scope.launch { drawerState.close() }
						viewModel.navigateTo(Screen.Perfil)
					}
				)
			}
		}
	) {
		Scaffold(
			topBar = {
				TopAppBar(
					title = { Text("Sleepy Hollow Store") },
					navigationIcon = {
						IconButton(onClick = {
							scope.launch { drawerState.open() }
						}) {
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
				Text("¡Bienvenido a la Página de Inicio !")
				Spacer(modifier = Modifier.height(16.dp))
				Button(onClick = { viewModel.navigateTo(Screen.Login) }) {
					Text("Iniciar Sesión")
				}
			}
		}
	}
}

// 🧩 Versión para vista previa (sin NavController ni ViewModel reales)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
	// Dummy NavController y ViewModel no se usan aquí
	Scaffold {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(it)
				.padding(15.dp),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			Text("Vista previa de HomeScreen")
		}
	}
}

