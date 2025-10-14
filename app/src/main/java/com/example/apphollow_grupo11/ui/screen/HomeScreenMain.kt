

// Pantalla principal de la app de venta de ropa. Aquí se muestran elementos visuales básicos y se aplican buenas prácticas de Jetpack Compose y Material3.
package com.example.apphollow_grupo11.ui.screen

import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.apphollow_grupo11.ui.utils.obtenerWindowSizeClass
import com.example.apphollow_grupo11.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
	navController: NavController,
	viewModel: MainViewModel = viewModel()
) {
	val windowSizeClass = obtenerWindowSizeClass()
	when (windowSizeClass.widthSizeClass) {
		WindowWidthSizeClass.Compact -> HomeScreenCompacta()
		WindowWidthSizeClass.Medium -> HomeScreenMediana()
		WindowWidthSizeClass.Expanded -> HomeScreenExtendida()
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

@Preview(name = "Compacta", widthDp = 360, heightDp = 800)
@Composable
fun PreviewCompacta() {
	HomeScreenCompacta()
}

@Preview(name = "Mediana", widthDp = 600, heightDp = 800)
@Composable
fun PreviewMediana() {
	HomeScreenMediana()
}

@Preview(name = "Extendida", widthDp = 840, heightDp = 800)
@Composable
fun PreviewExtendida() {
	HomeScreenExtendida()
}
