package com.example.apphollow_grupo11.viewmodel

import androidx.lifecycle.ViewModel
import com.example.apphollow_grupo11.model.UserError
import com.example.apphollow_grupo11.model.UserUiEstado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    // 🔹 DataStore para guardar los datos del usuario
    private val estadoDataStore = EstadoDataStore(application)

    // 🔹 Estado del formulario (registro)
    private val _estado = MutableStateFlow(UserUiEstado())
    val estado: StateFlow<UserUiEstado> = _estado

    // 🔹 Estado del usuario actual (sesión)
    private val _usuario = MutableStateFlow<UserUiEstado?>(null)
    val usuario: StateFlow<UserUiEstado?> = _usuario

    // -----------------------------
    //  Funciones de actualización
    // -----------------------------

    fun onNombreChange(valor: String) {
        _estado.update { it.copy(nombre = valor, errores = it.errores.copy(nombre = null)) }
    }

    fun onCorreoChange(valor: String) {
        _estado.update { it.copy(correo = valor, errores = it.errores.copy(correo = null)) }
    }

    fun onClaveChange(valor: String) {
        _estado.update { it.copy(clave = valor, errores = it.errores.copy(clave = null)) }
    }

    fun onDireccionChange(valor: String) {
        _estado.update { it.copy(direccion = valor, errores = it.errores.copy(direccion = null)) }
    }

    fun onAceptarTerminosChange(valor: Boolean) {
        _estado.update { it.copy(aceptaTerminos = valor) }
    }

    // -----------------------------
    // ✅ Validación del formulario
    // -----------------------------
    fun validarFormulario(): Boolean {
        val estadoActual = _estado.value
        var errores = UserError(
            nombre = if (estadoActual.nombre.isBlank()) "Campo obligatorio" else null,
            correo = if (!estadoActual.correo.contains("@")) "Correo inválido" else null,
            clave = if (estadoActual.clave.length < 6) "Debe tener al menos 6 caracteres" else null,
            direccion = if (estadoActual.direccion.isBlank()) "Campo obligatorio" else null
        )

        val aceptaTerminosValido = estadoActual.aceptaTerminos

        val hayErrores = listOfNotNull(
            errores.nombre,
            errores.correo,
            errores.clave,
            errores.direccion
        ).isNotEmpty()

        _estado.update { it.copy(errores = errores) }

        // 🚫 No deja avanzar si no aceptó términos
        return !hayErrores && aceptaTerminosValido
    }

    // -----------------------------
    // 💾 Guardar usuario en DataStore
    // -----------------------------
    fun guardarUsuario() {
        val usuarioActual = _estado.value
        viewModelScope.launch {
            estadoDataStore.guardarUsuario(
                nombre = usuarioActual.nombre,
                correo = usuarioActual.correo
            )
            _usuario.value = usuarioActual
        }
    }

    // -----------------------------
    //  Cargar usuario desde DataStore
    // -----------------------------
    fun cargarUsuario() {
        viewModelScope.launch {
            val datos = estadoDataStore.obtenerUsuario().first()
            if (datos.first != null && datos.second != null) {
                _usuario.value = UserUiEstado(
                    nombre = datos.first ?: "",
                    correo = datos.second ?: ""
                )
            }
        }
    }

    // -----------------------------
    // Cerrar sesión
    // -----------------------------
    fun cerrarSesion() {
        viewModelScope.launch {
            estadoDataStore.limpiarUsuario()
            _usuario.value = null
        }
    }
}
