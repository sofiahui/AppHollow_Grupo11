package com.example.apphollow_grupo11.model

data class UserUiEstado(

    val nombre : String = "",
    val correo : String = "",
    val clave : String = "",
    val direccion : String = "",
    val aceptaTerminos : Boolean = false,
    val errores : UserError = UserError(),
)
