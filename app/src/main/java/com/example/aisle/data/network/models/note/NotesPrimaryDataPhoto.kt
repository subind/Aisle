package com.example.aisle.data.network.models.note

import com.google.gson.annotations.SerializedName

data class NotesPrimaryDataPhoto(
    var photo: String,
    @SerializedName("status")
    var isProfilePic: String
)
