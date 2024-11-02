package com.example.musicappui.ui

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import com.example.musicappui.R

@Composable
fun BrowseView() {
    val categories =
        listOf("Podcast", "Gaming", "Mixes", "Chill-out music", "Recently uploaded", "New to you")

    LazyVerticalGrid(

        columns = GridCells.Fixed(2),

        ) {
        items(categories) {
            BrowseItem(category = it, resource = R.drawable.baseline_search_24)

        }
    }
}
