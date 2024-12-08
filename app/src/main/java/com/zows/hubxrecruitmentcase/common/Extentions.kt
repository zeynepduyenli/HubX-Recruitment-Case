package com.zows.hubxrecruitmentcase.common

import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowInsetsController
import androidx.core.util.TypedValueCompat.dpToPx

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
        val flags = if (isLightText) {
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS.inv()
        } else {
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        }
        this.decorView.systemUiVisibility = flags
    }
}

fun View.setStartMargin(start: Int? = null) {
    val displayMetrics = resources.displayMetrics
    layoutParams<ViewGroup.MarginLayoutParams> {
        start?.run { leftMargin = dpToPx(this.toFloat(), displayMetrics).toInt() }
    }
}

inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}