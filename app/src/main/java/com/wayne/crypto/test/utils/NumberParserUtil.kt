package com.wayne.crypto.test.utils

import java.math.BigDecimal

/**
 * Created by wayne.liu on 2020/9/9
 */
object NumberParserUtil {

    fun parseInt(value: String?, defaultValue: Int): Int {
        return try {
            value?.toInt() ?: defaultValue
        } catch (var3: NumberFormatException) {
            defaultValue
        }
    }

    fun parseBigDecimal(value: String?): BigDecimal? {
        if (value != null) {
            try {
                return BigDecimal(value)
            } catch (var2: NumberFormatException) {
            }
        }
        return null
    }

    fun parseBigDecimal(value: String?, defaultValue: BigDecimal = BigDecimal.ZERO): BigDecimal {
        return parseBigDecimal(value) ?: defaultValue
    }
}