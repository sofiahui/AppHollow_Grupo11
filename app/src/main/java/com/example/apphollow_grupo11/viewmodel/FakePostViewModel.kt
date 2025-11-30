package com.example.apphollow_grupo11.viewmodel

import com.example.apphollow_grupo11.model.Post


class FakePostViewModel : PostViewModel() {
    fun setFakePosts(posts: List<Post>) {
        _postList.value = posts
    }

    override fun fetchPosts() {
        // no llama al backend en tests
    }
}
