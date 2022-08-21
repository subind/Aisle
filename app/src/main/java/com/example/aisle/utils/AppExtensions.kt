package com.example.aisle.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.aisle.data.network.models.note.NotesPrimaryDataPhoto
import com.example.aisle.ui.home.notes.models.NotesRv
import com.squareup.picasso.Picasso

fun String.insertSpaceAfterCountryCode(): String {
    val countryCode = this.toCharArray(startIndex = 0, endIndex = 2)
    val phoneNumber = this.toCharArray(startIndex = 3, endIndex = this.length)
    return "${String(countryCode)} ${String(phoneNumber)}"
}

fun ProgressBar.isVisible(visibility: Boolean) {
    if(visibility) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun List<NotesPrimaryDataPhoto>.parseAndExtractStatusProfilePic(): String {
    this.forEach {
        return if(it.isProfilePic.equals("avatar", true)) {
            it.photo
        } else {
            ""
        }
    }
    return ""
}

fun MutableList<NotesRv>.addNotesTitleSection(note: NotesRv): MutableList<NotesRv> {
    this.add(0, note)
    return this
}

fun MutableList<NotesRv>.addNotesUpgradeSection(note: NotesRv): MutableList<NotesRv> {
    if(this.size >= 2) {
        this.add(2, note)
    } else {
        this.add(1, note)
    }
    return this
}

fun MutableList<NotesRv>.extractAndInsertNotesRv(tempNotes: MutableList<NotesRv>): MutableList<NotesRv> {
    tempNotes.forEach {
        this.add(it)
    }
    return this
}

fun ImageView.loadImageHelper(imageUri: String) {
    if(imageUri.isNotEmpty()) {
        Picasso.get().load(imageUri).into(this)
    }
}