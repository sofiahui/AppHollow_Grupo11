package com.example.apphollow_grupo11.viewmodel

import androidx.lifecycle.ViewModel
import com.example.apphollow_grupo11.model.LoginUiState
import com.example.apphollow_grupo11.model.LoginErrores
import com.example.apphollow_grupo11.network.RetrofitInstance
import com.example.apphollow_grupo11.data.LoginResponse
import com.example.apphollow_grupo11.data.LoginRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    // Estado del formulario de login
    private val _estado = MutableStateFlow(LoginUiState())
    val estado: StateFlow<LoginUiState> = _estado

    // Estado del usuario logeado
    private val _usuarioLogeado = MutableStateFlow<LoginResponse?>(null)
    val usuarioLogeado: StateFlow<LoginResponse?> = _usuarioLogeado


    // Funciones para actualizar los campos del formulario
    fun onUsuarioChange(valor: String) {
        _estado.update { it.copy(usuario = valor, errores = it.errores.copy(usuario = null)) }
    }

    fun onClaveChange(valor: String) {
        _estado.update { it.copy(clave = valor, errores = it.errores.copy(clave = null)) }
    }

    /**
     * Realiza el proceso de login validando los campos y llamando a la API.
     * @return true si el login fue exitoso, false en caso contrario.
     */
    suspend fun login(): Boolean {
        val usuario = _estado.value.usuario.trim()
        val clave = _estado.value.clave.trim()

        var errores = LoginErrores()

        if (usuario.isEmpty()) errores = errores.copy(usuario = "Campo requerido")
        if (clave.isEmpty()) errores = errores.copy(clave = "Campo requerido")

        if (errores.usuario != null || errores.clave != null) {
            _estado.update { it.copy(errores = errores) }
            return false
        }

        // acá se construye el request para la API
        val request = LoginRequest(email = usuario, password = clave)

        // Llamada a la API base dato, se envía el request payload
        return try {
            val response = RetrofitInstance.instance.login(request)

            if (response.isSuccessful) {
                val body = response.body() ?: return false

                _usuarioLogeado.value = body
                _estado.update { it.copy(errores = LoginErrores()) }
                true

            } else {
                _estado.update {
                    it.copy(errores = it.errores.copy(usuario = "Usuario o contraseña incorrectos"))
                }
                false
            }

        } catch (e: Exception) {
            _estado.update {
                it.copy(errores = it.errores.copy(usuario = "Error de conexión: ${e.message}"))
            }
            false
        }
    }
}
