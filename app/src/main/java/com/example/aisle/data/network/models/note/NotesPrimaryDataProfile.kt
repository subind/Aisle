package com.example.aisle.data.network.models.note

import com.google.gson.annotations.SerializedName

data class NotesPrimaryDataProfile(
    var age: Int,
    @SerializedName("first_name")
    var name: String,
    var gender: String,
)
