package com.example.aisle.ui.home.notes.models

data class Notes(
    var titleSection: NotesTitle? = null,
    var primaryProfile: NotesProfile? = null,
    var upgradeSection: NotesUpgrade? = null,
    var secondaryProfiles: NotesProfile? = null
)
