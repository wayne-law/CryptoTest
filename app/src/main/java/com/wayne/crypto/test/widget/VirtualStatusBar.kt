package com.wayne.crypto.test.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.wayne.crypto.test.utils.SystemUiUtil
import kotlin.math.min

class VirtualStatusBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(
            getDefaultSize2(suggestedMinimumWidth, widthMeasureSpec),
            getDefaultSize2(SystemUiUtil.getStatusBarHeight(context), heightMeasureSpec)
        )
    }

    private fun getDefaultSize2(size: Int, measureSpec: Int): Int {
        var result = size
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        when (specMode) {
            MeasureSpec.UNSPECIFIED -> result = size
            MeasureSpec.AT_MOST -> result = min(size, specSize)
            MeasureSpec.EXACTLY -> result = specSize
        }
        return result
    }

}