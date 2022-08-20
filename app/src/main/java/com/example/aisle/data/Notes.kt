package com.example.aisle.data

data class Notes(
    var titleSection: NotesTitle,
    var primaryProfile: NotesProfile,
    var upgradeSection: NotesUpgrade,
    var secondaryProfiles: List<NotesProfile>
)
