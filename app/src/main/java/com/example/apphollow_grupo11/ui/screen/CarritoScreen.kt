// CarritoScreen.kt
// Pantalla para mostrar y gestionar los productos agregados al carrito de compras.
// Aquí se implementa la UI para modificar cantidades, eliminar productos y proceder al pago.
package com.example.apphollow_grupo11.ui.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavController
import com.example.apphollow_grupo11.viewmodel.MainViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.apphollow_grupo11.model.Producto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    // Ejemplo temporal de lista de productos (luego puedes usar ViewModel)
    val productos = remember {
        listOf(
            Producto("Polera Oversized", 15990.0, 1),
            Producto("Pantalón Cargo", 19990.0, 2),
            Producto("Chaqueta de Jean", 24990.0, 1)
        )
    }

    val total = productos.sumOf { it.precio * it.cantidad }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carrito de Compras") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            if (productos.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Tu carrito está vacío 🛒")
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(productos) { producto ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                                    Text("Cantidad: ${producto.cantidad}")
                                }
                                Text(
                                    text = "$${producto.precio * producto.cantidad}",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Total y botón de compra
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total:",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "$${"%.0f".format(total)}",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /* Aquí puedes navegar a la pantalla de pago */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Finalizar compra")
                }
            }
        }
    }
}