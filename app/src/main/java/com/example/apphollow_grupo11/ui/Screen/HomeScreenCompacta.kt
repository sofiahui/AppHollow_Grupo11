package com.example.apphollow_grupo11.ui.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.apphollow_grupo11.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenCompacta(){
    Scaffold (
        topBar = {
            TopAppBar(title = { Text(text="Sleepy Hollow Store") })
        }
    ){
            innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "¡Bienvenido Compacta!",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge
            )
            Button(onClick = {}) {
                Text("Presióname")
            }

            Image(
                painter = painterResource(id= R.drawable.logo_png),
                contentDescription = "Logo App Compacta",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Fit
            )

        }
    }
}
