package com.example.aisle.utils

import android.app.Activity
import android.view.WindowManager

class AppUtils {

    companion object {
        const val BASE_URL = "https://testa2.aisle.co/V1/"

        fun disableUserUiInteraction(activity: Activity) {
            activity.window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        }

        fun enableUserUiInteraction(activity: Activity) {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }

}