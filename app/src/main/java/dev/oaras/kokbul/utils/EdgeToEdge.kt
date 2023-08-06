/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.utils

import android.app.Activity
import androidx.core.view.WindowCompat


object EdgeToEdge {
    fun setTranslucent(activity: Activity) {
//        activity.window.setFlags(
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        )
        WindowCompat.setDecorFitsSystemWindows(activity.window, false)
    }
}