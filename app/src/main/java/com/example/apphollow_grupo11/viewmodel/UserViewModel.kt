package com.example.apphollow_grupo11.viewmodel


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
import com.example.apphollow_grupo11.data.ApiEstado
import com.example.apphollow_grupo11.data.UserRequest
import com.example.apphollow_grupo11.network.RetrofitInstance

class UserViewModel(application: Application) : AndroidViewModel(application) {

    // 🔹 DataStore para guardar los datos del usuario
    private val estadoDataStore = EstadoDataStore(application)

    // 🔹 Estado del formulario (registro)
    private val _estado = MutableStateFlow(UserUiEstado())
    val estado: StateFlow<UserUiEstado> = _estado

    // 🔹 Estado del usuario actual (sesión)
    private val _usuario = MutableStateFlow<UserUiEstado?>(null)
    val usuario: StateFlow<UserUiEstado?> = _usuario

    // 🔹 Estado de la API (registro)
    private val _estadoApi = MutableStateFlow<ApiEstado>(ApiEstado.Idle)
    val estadoApi: StateFlow<ApiEstado> = _estadoApi


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
    // Registrar usuario (simulación API)
    // -----------------------------
    fun registrarUsuario() {
        val datos = _estado.value

        val request = UserRequest(
            name = datos.nombre,
            email = datos.correo,
            passwordHash = datos.clave,
            address = datos.direccion
        )

        viewModelScope.launch {
            _estadoApi.value = ApiEstado.Cargando

            try {
                val respuesta = RetrofitInstance.instance.registrarUsuario(request)

                if (respuesta.isSuccessful) {
                    _estadoApi.value = ApiEstado.Exito("Usuario registrado con éxito")
                } else {
                    _estadoApi.value = ApiEstado.Error("Error: ${respuesta.code()}")
                }

            } catch (e: Exception) {
                e.printStackTrace() // ver en logcat la traza completa
                _estadoApi.value = ApiEstado.Error("No se pudo conectar al servidor: ${e.message}")
            }
        }
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

    // -----------------------------
    // Reset estado API
    // -----------------------------
    fun resetApiEstado() {
        _estadoApi.value = ApiEstado.Idle
    }
}
