package com.zows.hubxrecruitmentcase.common

import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import android.widget.TextView

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

fun TextView.setVisibilityBasedOnText() {
    visibility = if (text.isNullOrBlank()) View.GONE else View.VISIBLE
}

fun View.setVisibilityBasedOnCondition(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}

fun List<TextView>.hideIfAllTextIsBlank() {
    val isAllTextEmpty = all { it.text.isNullOrBlank() }
    if (isAllTextEmpty) {
        forEach { it.visibility = View.GONE }
    }
}
