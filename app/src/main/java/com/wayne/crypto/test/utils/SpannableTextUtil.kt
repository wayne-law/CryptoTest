package com.wayne.crypto.test.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorInt

/**
 * Created by wayne.liu on 2020/9/10
 */
object SpannableTextUtil {

    fun createSpannableText(prefix: String = "",
                            text: String,
                            postfix: String = "",
                            @ColorInt textColor: Int,
                            textSize: Int): SpannableString {
        val totalText = buildString {
            append(prefix)
            append(text)
            append(postfix)
        }
        val totalSpannableString = SpannableString(totalText)
        totalSpannableString.setSpan(
            ForegroundColorSpan(textColor),
            prefix.length,
            prefix.length + text.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        if (textSize <= 0) {
            return totalSpannableString
        }
        totalSpannableString.setSpan(
            AbsoluteSizeSpan(textSize, true),
            prefix.length,
            prefix.length + text.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return totalSpannableString
    }
}