package com.example.apphollow_grupo11.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(
    val id: Int,
    val name: String,
    val email: String,
    val address: String,
    val admin: Boolean,
    val active: Boolean
) : Parcelable