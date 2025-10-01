
// HomeScreen.kt
// Pantalla principal de la app de venta de ropa. Aquí se muestran elementos visuales básicos y se aplican buenas prácticas de Jetpack Compose y Material3.

package com.example.apphollow_grupo11.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
	// Scaffold proporciona la estructura visual básica (barra superior, contenido, etc.)
	Scaffold(
		topBar = {
			// Barra superior personalizada
			TopAppBar(
				title = { Text("Sleepy Hollow Store") },
				
			)
		}
	) { innerPadding ->
		// Contenido principal de la pantalla
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(innerPadding)
				.padding(15.dp), // Espaciado externo
			verticalArrangement = Arrangement.spacedBy(24.dp), // Espaciado uniforme entre elementos
			horizontalAlignment = Alignment.CenterHorizontally // Alineación central
		) {
			// Título principal
			Text(
				text = "Bienvenido a Sleepy Hollow Store",
				
			)
			// Elemento visual extra: botón de ejemplo
			Button(onClick = { /* Acción de ejemplo */ }) {
				Text("Ver productos")
			}
			
            Image(
                painter = painterResource(id = R.drawable.tu_imagen), // Reemplaza con tu recurso de imagen
                contentDescription = "Logo de la tienda",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                    contentScale=ContentScale.Fit
            )
			// Puedes agregar más elementos visuales aquí siguiendo la documentación oficial
		}
	}
    @Preview(showBackground = true)
    @Composable
    fun HomeScreenPreview() {   
        HomeScreen()
    }
}

// Cada sección está comentada para explicar su función y buenas prácticas.
// Puedes personalizar colores, tipografías y agregar más elementos según la guía de Material3 y Jetpack Compose.
