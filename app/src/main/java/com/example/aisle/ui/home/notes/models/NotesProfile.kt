package com.example.aisle.ui.home.notes.models

data class NotesProfile(
    var pic: String,
    var name: String,
    var age: Int = 0,
    var isProfilePicBlurred: Boolean = false,
)