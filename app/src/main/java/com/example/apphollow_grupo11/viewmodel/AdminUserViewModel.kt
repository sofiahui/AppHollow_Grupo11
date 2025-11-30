package com.example.apphollow_grupo11.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.apphollow_grupo11.data.UserResponse
import com.example.apphollow_grupo11.network.RetrofitInstance

class AdminUserViewModel : ViewModel() {

    private val _listaUsuarios = MutableStateFlow<List<UserResponse>>(emptyList())
    val listaUsuarios: StateFlow<List<UserResponse>> = _listaUsuarios

    fun obtenerUsuarios() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.instance.obtenerUsuarios()

                if (response.isSuccessful) {
                    _listaUsuarios.value = response.body()?.filter { it.active } ?: emptyList()
                } else {
                    println("❌ Error de API: ${response.code()} - ${response.errorBody()}")
                }

            } catch (e: Exception) {
                println("❌ Excepción al obtener usuarios: ${e.message}")
            }
        }
    }

    fun editarUsuario(id: Int, datos: UserResponse) {
        viewModelScope.launch {
            try {
                // obtener usuario completo para preservar campos que no se editan
                val original = fetchFullUser(id) ?: return@launch

                val request = original.copy(
                    name = datos.name,
                    email = datos.email,
                    address = datos.address,
                    admin = datos.admin,
                    active = datos.active
                )

                val response = RetrofitInstance.instance.updateUser(id, request)

                if (response.isSuccessful) {
                    val usuarioActualizado = response.body()!!

                    _listaUsuarios.value = _listaUsuarios.value.map {
                        if (it.id == usuarioActualizado.id) usuarioActualizado else it
                    }

                } else {
                    println("❌ Error al actualizar: ${response.code()}")
                }

            } catch (e: Exception) {
                println("❌ Excepción: ${e.message}")
            }
        }
    }

    fun desactivarUsuario(id: Int) {
        viewModelScope.launch {
            try {
                // obtener usuario completo para preservar campos que no se editan
                val original = fetchFullUser(id) ?: return@launch
                val request = original.copy(active = false)

                val response = RetrofitInstance.instance.updateUser(id, request)

                if (response.isSuccessful) {
                    // Quitar al usuario desactivado del listado (solo mostrar activos)
                    _listaUsuarios.value = _listaUsuarios.value.filter { it.id != id }
                } else {
                    println("❌ Error al desactivar: ${response.code()}")
                }

            } catch (e: Exception) {
                println("❌ Excepción al desactivar usuario: ${e.message}")
            }
        }
    }


    // Helper: obtener la versión completa del usuario desde la API
    private suspend fun fetchFullUser(id: Int): UserResponse? {
        return try {
            val resp = RetrofitInstance.instance.obtenerUsuario(id) // ajusta nombre si es distinto
            if (resp.isSuccessful) resp.body() else {
                println("❌ Error al obtener usuario $id: ${resp.code()}")
                null
            }
        } catch (e: Exception) {
            println("❌ Excepción al obtener usuario $id: ${e.message}")
            null
        }
    }


}