package com.example.apphollow_grupo11.viewmodel

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
//  Declaramos el DataStore con nombre 'preferencias_usuario'
val Context.dataStore by preferencesDataStore(name = "preferencias_usuario")

class EstadoDataStore(private val context: Context) {

    // Clave usada para guardar el estado
    private val ESTADO_ACTIVADO = booleanPreferencesKey(name = "modo_activado")
    private val NOMBRE_USUARIO = stringPreferencesKey("nombre_usuario")
    private val CORREO_USUARIO = stringPreferencesKey("correo_usuario")
    //  Función para guardar el estado en DataStore

    suspend fun guardarEstado(valor: Boolean) {
        context.dataStore.edit { preferencias ->
            preferencias[ESTADO_ACTIVADO] = valor
        }
    }

    fun obtenerEstado(): Flow<Boolean?> {
        return context.dataStore.data.map { it[ESTADO_ACTIVADO] }
    }

    // Guardar los datos del usuario
    suspend fun guardarUsuario(nombre: String, correo: String) {
        context.dataStore.edit { preferencias ->
            preferencias[NOMBRE_USUARIO] = nombre
            preferencias[CORREO_USUARIO] = correo
        }
    }

    // Obtener los datos del usuario
    fun obtenerUsuario(): Flow<Pair<String?, String?>> {
        return context.dataStore.data.map { preferencias ->
            Pair(
                preferencias[NOMBRE_USUARIO],
                preferencias[CORREO_USUARIO]
            )
        }
    }

    // Borrar datos (para cerrar sesión)
    suspend fun limpiarUsuario() {
        context.dataStore.edit { preferencias ->
            preferencias.remove(NOMBRE_USUARIO)
            preferencias.remove(CORREO_USUARIO)
        }
    }
}

