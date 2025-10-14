package com.example.apphollow_grupo11.viewmodel

import androidx.lifecycle.ViewModel
import com.example.apphollow_grupo11.model.UserError
import com.example.apphollow_grupo11.model.UserUiEstado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

// UserViewModel.kt
// ViewModel encargado de la gestión del usuario, su perfil y autenticación.
// Aquí se manejarán los datos del usuario, inicio de sesión, registro y actualización de perfil.
class UserViewModel : ViewModel() {
    private val _estado = MutableStateFlow(UserUiEstado())

    val estado : StateFlow<UserUiEstado> = _estado

    fun onNombreChange(valor:String){
        _estado.update { it.copy(nombre = valor, errores = it.errores.copy(nombre = null)) }
    }

    fun onCorreoChange(valor:String){
        _estado.update { it.copy(correo = valor, errores = it.errores.copy(correo = null)) }
    }

    fun onClaveChange(valor:String){
        _estado.update { it.copy(clave = valor, errores = it.errores.copy(clave = null)) }
    }

    fun onDireccionChange(valor:String){
        _estado.update { it.copy(direccion = valor, errores = it.errores.copy(direccion = null)) }
    }

    fun onAceptarTerminosChange(valor: Boolean){
        _estado.update { it.copy(aceptaTerminos = valor) }
    }

    fun validarFormulario(): Boolean{
        val estadoActual = _estado.value
        val errores = UserError(
            nombre = if (estadoActual.nombre.isBlank()) "Campo obligatorio" else null,
            correo = if (!estadoActual.correo.contains("@")) "Correo invalido" else null,
            clave = if (estadoActual.clave.length < 6) "Debe tener al menos 6 caracteres" else null,
            direccion = if (estadoActual.direccion.isBlank()) "Campo obligatorio" else null
        )

        val hayErrrores = listOfNotNull(
            errores.nombre,
            errores.correo,
            errores.clave,
            errores.direccion
        ).isNotEmpty()

        _estado.update { it.copy( errores = errores) }

        return !hayErrrores
    }
}