package com.example.apphollow_grupo11.data

sealed class ApiEstado {
    object Idle : ApiEstado()
    object Cargando : ApiEstado()
    data class Exito(val mensaje: String) : ApiEstado()
    data class Error(val mensaje: String) : ApiEstado()
}