package com.example.apphollow_grupo11.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.apphollow_grupo11.viewmodel.PostViewModel

@Composable
fun PostScreen(viewModel: PostViewModel) {

    val posts by viewModel.postList.collectAsState()

    Column {
        LazyColumn {
            items(posts) { post ->
                Text(text = post.title)
            }
        }
    }
}
