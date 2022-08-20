package com.example.aisle.data.network.models.note

import com.google.gson.annotations.SerializedName

data class NotesSecondaryProfile(
    @SerializedName("first_name")
    var name: String,
    @SerializedName("avatar")
    var profilePic: String
)
