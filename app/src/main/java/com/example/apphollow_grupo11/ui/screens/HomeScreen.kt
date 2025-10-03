
// HomeScreen.kt
// Pantalla principal de la app de venta de ropa. Aquí se muestran elementos visuales básicos y se aplican buenas prácticas de Jetpack Compose y Material3.

package com.example.apphollow_grupo11.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.apphollow_grupo11.R

@OptIn(ExperimentalMaterial3Api::class)


@Composable
fun HomeScreen(
	navController: NavController,
	viewModel: MainViewModel = viewModel()
) {
	val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
	val scope = rememberCoroutineScope()

	ModalNavigationDrawer(
		drawerState = drawerState,
		drawerContent = {
			ModalDrawerSheet {
				Text(text = "Menú", modifier = Modifier.padding(all = 16.dp))
				NavigationDrawerItem(
					label = { Text(text = "Ir a Perfil") },
					selected = false,
					onClick = {
						// Acción al hacer click
						scope.launch { drawerState.close() }
						viewModel.navigateTo(Screen.Profile)
					}
				)

           }
       }
	)

  Scaffold(
   topBar = {
	TopAppBar(
		title = { Text(text = "Sleepy Hollow Store") },
		navigationIcon = {
			IconButton(onClick = {
				scope.launch { drawerState.open() }
			}) {
				Icon(imageVector = Icons.Default.Menu, contentDescription = "Menú")
			}
		}
	)
}
) { innerPadding ->
	  Column(
		  modifier = Modifier
			  .padding(paddingValues = innerPadding)
			  .fillMaxSize(),
		  horizontalAlignment = Alignment.CenterHorizontally,
		  verticalArrangement = Arrangement.Center
	  ) {
		  Text(text = "¡Bienvenido a la Página de Inicio (MVVM)!")
		  Spacer(modifier = Modifier.height(height = 16.dp))
		  Button(onClick = { viewModel.navigateTo(Screen.Settings) }) {
			  Text(text = "Ir a Configuración")
		  }
	  }
  }


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
	HomeScreen()
}

// Cada sección está comentada para explicar su función y buenas prácticas.
// Puedes personalizar colores, tipografías y agregar más elementos según la guía de Material3 y Jetpack Compose.
