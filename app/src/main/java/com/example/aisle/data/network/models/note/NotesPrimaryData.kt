package com.example.aisle.data.network.models.note

import com.google.gson.annotations.SerializedName

data class NotesPrimaryData(
    @SerializedName("photos")
    var profilePicData: List<NotesPrimaryDataPhoto>,
    @SerializedName("general_information")
    var profileData: NotesPrimaryDataProfile,
)
