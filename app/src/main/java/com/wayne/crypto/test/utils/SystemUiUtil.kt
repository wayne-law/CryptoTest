package com.wayne.crypto.test.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import kotlin.math.ceil

/**
 * Created by wayne.liu on 2020/9/8
 */
internal object SystemUiUtil {

    fun setLightStatusBar(activity: Activity, light: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val window = activity.window
            val old = window.decorView.systemUiVisibility
            val flag = if (light) {
                old or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                old and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
            window.decorView.systemUiVisibility = flag
        }
    }

    /**
     * Get the height of status bar
     *
     * @return the height of status bar.
     */
    fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId != 0) {
            context.resources.getDimensionPixelSize(resourceId)
        } else {
            val heightInDp = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                24
            } else {
                25
            }
            ceil((heightInDp * context.resources.displayMetrics.density).toDouble()).toInt()
        }
    }

    fun setLayoutStableFullScreen(window: Window) {
        window.decorView.systemUiVisibility = (window.decorView.systemUiVisibility
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
    }

    fun setDisplayCutoutModeShortEdges(window: Window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
    }
}