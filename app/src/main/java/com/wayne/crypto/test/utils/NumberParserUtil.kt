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

    fun parseInteger(value: String?, defaultValue: Int): Int {
        return try {
            value?.toInt() ?: defaultValue
        } catch (var3: NumberFormatException) {
            defaultValue
        }
    }

    fun parseIntValue(value: String?, defaultValue: Int): Int {
        val result = parseBigDecimal(value)
        return result?.toInt() ?: defaultValue
    }

    fun parseLong(value: String?, defaultValue: Long): Long {
        return try {
            value?.toLong() ?: defaultValue
        } catch (var4: NumberFormatException) {
            defaultValue
        }
    }

    fun parseLongValue(value: String?, defaultValue: Long): Long {
        val result = parseBigDecimal(value)
        return result?.toLong() ?: defaultValue
    }

    fun parseFloat(value: String?, defaultValue: Float): Float {
        if (value != null) {
            try {
                return value.toFloat()
            } catch (var3: NumberFormatException) {
            }
        }
        return defaultValue
    }

    fun parseDouble(value: String?, defaultValue: Double): Double {
        val valueD = parseDouble(value)
        return if (java.lang.Double.isNaN(valueD)) defaultValue else valueD
    }

    private fun parseDouble(value: String?): Double {
        if (value != null) {
            try {
                return value.toDouble()
            } catch (var2: NumberFormatException) {
            }
        }
        return 0.0 / 0.0
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