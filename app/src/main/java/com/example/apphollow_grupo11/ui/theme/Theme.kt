// Theme.kt
// Archivo para configurar el tema principal de la aplicación usando Material3 y Jetpack Compose.
// Aquí se integran los colores, tipografías y estilos globales.
package com.example.apphollow_grupo11.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = GothicLila,
    onPrimary = Color.White,
    secondary = GothicLilaLight,
    onSecondary = Color.White,
    background = GothicBackground,
    onBackground = GothicTextPrimary,
    surface = GothicSurface,
    onSurface = GothicTextPrimary
)

private val LightColorScheme = lightColorScheme(
    primary = GothicLila,
    onPrimary = Color.White,
    secondary = GothicLilaLight,
    background = Color(0xFF7E16B0),
    surface = Color(0xFF9F17C9),
    onBackground = Color(0xFF1E1B24),
    onSurface = Color(0xFF1E1B24)
)


    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */


@Composable
fun AppHollow_Grupo11Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}