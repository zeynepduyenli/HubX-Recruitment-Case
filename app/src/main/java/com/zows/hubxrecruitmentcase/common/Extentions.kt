package com.zows.hubxrecruitmentcase.common

import android.os.Build
import android.view.Window
import android.view.WindowInsetsController
import androidx.fragment.app.FragmentActivity

/**
 * Extension function to set the status bar text color.
 * @param isLightText If true, sets the status bar text to white (light). Otherwise, it sets to dark.
 */
fun Window.setStatusBarTextColor(isLightText: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val controller = this.decorView.windowInsetsController
        if (isLightText) {
            controller?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS.inv(),
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else {
            controller?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        }
    } else {
        // For versions below Android 11, use traditional methods.
        val flags = if (isLightText) {
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS.inv()
        } else {
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        }
        this.decorView.systemUiVisibility = flags
    }
}
