package com.example.apphollow_grupo11.viewmodel

import com.example.apphollow_grupo11.model.Post


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakePostViewModel(
    private val fakePosts: List<Post> = emptyList()
) : PostViewModel() {

    fun setFakePosts(posts: List<Post>) {
        _postList.value = posts
    }

    override fun fetchPost() {
        _postList.value = fakePosts
    }
}
