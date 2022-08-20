package com.example.aisle.utils

class Common {

    companion object {
        const val BASE_URL = "https://testa2.aisle.co/V1/"

        fun String.insertSpaceAfterCountryCode(): String {
            val countryCode = this.toCharArray(startIndex = 0, endIndex = 2)
            val phoneNumber = this.toCharArray(startIndex = 3, endIndex = this.length)
            return "${String(countryCode)} ${String(phoneNumber)}"
        }
    }

}