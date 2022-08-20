package com.example.aisle.utils

import android.view.View
import android.widget.ProgressBar

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