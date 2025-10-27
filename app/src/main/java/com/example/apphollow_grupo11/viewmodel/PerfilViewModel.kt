package com.example.apphollow_grupo11.viewmodel



import androidx.lifecycle.ViewModel
import com.example.apphollow_grupo11.model.UserUiEstado
import com.example.apphollow_grupo11.model.UserError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PerfilViewModel : ViewModel() {

    private val _estado = MutableStateFlow(UserUiEstado())
    val estado: StateFlow<UserUiEstado> = _estado

    fun onNombreChange(valor: String) {
        _estado.update { it.copy(nombre = valor, errores = it.errores.copy(nombre = null)) }
    }

    fun onCorreoChange(valor: String) {
        _estado.update { it.copy(correo = valor, errores = it.errores.copy(correo = null)) }
    }

    fun onDireccionChange(valor: String) {
        _estado.update { it.copy(direccion = valor, errores = it.errores.copy(direccion = null)) }
    }

    fun validar(): Boolean {
        val estadoActual = _estado.value
        var errores = UserError()

        if (estadoActual.nombre.isBlank()) errores = errores.copy(nombre = "Campo requerido")
        if (estadoActual.correo.isBlank()) errores = errores.copy(correo = "Campo requerido")
        if (estadoActual.direccion.isBlank()) errores = errores.copy(direccion = "Campo requerido")

        val hayErrores = listOfNotNull(
            errores.nombre,
            errores.correo,
            errores.direccion
        ).isNotEmpty()

        _estado.update {
            it.copy(
                errores = errores
            )
        }

        return !hayErrores
    }
}
