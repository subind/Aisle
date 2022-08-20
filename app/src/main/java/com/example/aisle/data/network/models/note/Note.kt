package com.example.aisle.data.network.models.note

import com.google.gson.annotations.SerializedName

data class Note(
    @SerializedName("invites")
    var primaryProfile: NotesPrimary,
    @SerializedName("likes")
    var secondary: NotesSecondary
)
