package com.example.aisle.data.network.models.note

import com.google.gson.annotations.SerializedName

data class NotesSecondary(
    var profiles: List<NotesSecondaryProfile>,
    @SerializedName("can_see_profile")
    var isProfileVisible: Boolean
)
