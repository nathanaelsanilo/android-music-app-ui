package com.example.musicappui

import androidx.annotation.DrawableRes

data class Lib(@DrawableRes val icon: Int, val name: String)

val libraries = listOf<Lib>(
    Lib(R.drawable.baseline_queue_music_24, "Playlist"),
    Lib(R.drawable.baseline_auto_awesome_24, "Artists"),
    Lib(R.drawable.baseline_library_music_24, "Album"),
    Lib(R.drawable.baseline_music_note_24, "Songs"),
    Lib(R.drawable.baseline_topic_24, "Genre"),
)
