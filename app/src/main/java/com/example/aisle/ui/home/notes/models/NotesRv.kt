package com.example.aisle.ui.home.notes.models

data class NotesRv(
    var noteInfo: Notes,
    var viewHolderType: Int = 0
) {
    companion object {
        const val TITLE_SECTION = 111
        const val PRIMARY_SECTION = 222
        const val UPGRADE_SECTION = 333
        const val SECONDARY_SECTION = 444
    }
}
