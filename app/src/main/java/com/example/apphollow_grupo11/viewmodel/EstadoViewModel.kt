package com.example.apphollow_grupo11.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

//  ViewModel extendido desde AndroidViewModel porque usaremos contexto
class EstadoViewModel(application: Application) : AndroidViewModel(application) {

    //  DataStore creado con contexto de aplicación
    private val estadoDataStore = EstadoDataStore(context = application)

    //  Estado que representa si está "activado" o no (Observable)
    private val _activo = MutableStateFlow<Boolean?>(value = null)
    val activo: StateFlow<Boolean?> = _activo

    //  Estado para mostrar u ocultar el mensaje animado
    private val _mostrarMensaje = MutableStateFlow(value = false)
    val mostrarMensaje: StateFlow<Boolean> = _mostrarMensaje

    init {
        //  Al iniciar el ViewModel, cargamos el estado desde DataStore
        cargarEstado()
    }

    fun cargarEstado() {
        viewModelScope.launch {
            // 🟣 Simula demora para mostrar loader (opcional)
            delay(timeMillis = 1500)
            _activo.value = estadoDataStore.obtenerEstado().first() ?: false
        }
    }

    fun alternarEstado() {
        viewModelScope.launch {
            //  Alternamos el valor actual
            val nuevoValor = !(_activo.value ?: false)

            // Guardamos en DataStore
            estadoDataStore.guardarEstado(nuevoValor)

            // Actualizamos el flujo
            _activo.value = nuevoValor

            //  Mostramos el mensaje visual animado
            _mostrarMensaje.value = true

            delay(timeMillis = 2000) //  Ocultamos después de 2 segundos
            _mostrarMensaje.value = false
        }
    }

}
