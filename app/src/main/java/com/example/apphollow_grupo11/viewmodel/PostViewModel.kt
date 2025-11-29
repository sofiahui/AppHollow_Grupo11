package com.example.apphollow_grupo11.viewmodel;

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.apphollow_grupo11.model.Post

open class PostViewModel : ViewModel() {

        protected val _postList = MutableStateFlow<List<Post>>(emptyList())
        val postList: StateFlow<List<Post>> = _postList

        open fun fetchPosts() {
        // En la app real llamarías a un repositorio
        // Aquí queda vacío para que el test pueda sobrescribirlo
        }
        }