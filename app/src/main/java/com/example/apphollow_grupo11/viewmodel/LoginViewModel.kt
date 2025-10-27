package com.example.apphollow_grupo11.viewmodel

import androidx.lifecycle.ViewModel
import com.example.apphollow_grupo11.model.LoginUiState
import com.example.apphollow_grupo11.model.LoginErrores
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel()  {
    private val _estado = MutableStateFlow(LoginUiState())
    val estado: StateFlow<LoginUiState> = _estado

    fun onUsuarioChange(valor: String) {
        _estado.update { it.copy(usuario = valor, errores = it.errores.copy(usuario = null)) }
    }

    fun onClaveChange(valor: String) {
        _estado.update { it.copy(clave = valor, errores = it.errores.copy(clave = null)) }
    }

    fun validar(): Boolean {
        val estadoActual = _estado.value
        val usuario = _estado.value.usuario.trim()
        val clave = _estado.value.clave.trim()

        //if (usuario.isEmpty()) errores = errores.copy(usuario = "Campo requerido")
        //if (clave.isEmpty()) errores = errores.copy(clave = "Campo requerido")


        val errores = LoginErrores(
            usuario = if (estadoActual.usuario != "Admin") "Usuario incorrecto" else null,
            clave = if (estadoActual.clave != "admin1234") "Clave incorrecta" else null
        )

        val hayErrores = listOfNotNull(
            errores.usuario,
            errores.clave
        ).isNotEmpty()

        _estado.update { it.copy(errores = errores) }

        return usuario == "usuario1" && clave == "1234"
    }
}
