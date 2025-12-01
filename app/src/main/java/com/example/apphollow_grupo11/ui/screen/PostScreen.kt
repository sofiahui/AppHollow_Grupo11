package com.example.apphollow_grupo11.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.apphollow_grupo11.viewmodel.PostViewModel

@Composable
fun PostScreen(viewModel: PostViewModel,autoFetch: Boolean = true) {

    val posts = viewModel.postList.collectAsState().value

    if (autoFetch) {
        LaunchedEffect(Unit) {
            viewModel.fetchPost()
        }
    }



    LazyColumn {
        items(posts) { post ->
            Text(post.title)
        }
    }
}
