package com.example.apphollow_grupo11.data

data class UserRequest(
    val name: String,
    val email: String,
    val passwordHash: String,
    val address: String
)