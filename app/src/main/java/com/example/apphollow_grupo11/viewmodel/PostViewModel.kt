package com.example.apphollow_grupo11.viewmodel;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.apphollow_grupo11.model.Post
import com.example.apphollow_grupo11.repository.PostRepository
import kotlinx.coroutines.launch

open class PostViewModel : ViewModel() {

        private val repository = PostRepository()

        protected val _postList = MutableStateFlow<List<Post>>(emptyList())
        val postList: StateFlow<List<Post>> = _postList

        open fun fetchPost() {
        viewModelScope.launch {
            try {
                _postList.value = repository.getPosts()
            } catch (e: Exception) {
                println("Error al obtener datos: ${e.localizedMessage}")
            }
        }


        }
}