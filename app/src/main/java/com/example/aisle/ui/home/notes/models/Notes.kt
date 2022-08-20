package com.example.aisle.ui.home.notes.models

data class Notes(
    var titleSection: NotesTitle,
    var primaryProfile: NotesProfile,
    var upgradeSection: NotesUpgrade,
    var secondaryProfiles: List<NotesProfile>
)
