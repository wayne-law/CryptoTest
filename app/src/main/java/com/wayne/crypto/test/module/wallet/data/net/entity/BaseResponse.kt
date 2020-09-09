package com.wayne.crypto.test.module.wallet.data.net.entity

import com.google.gson.annotations.SerializedName
import com.wayne.crypto.test.constant.MiscValueConst

/**
 * Created by wayne.liu on 2020/9/9
 */
abstract class BaseResponse {

    @SerializedName("ok")
    val status: String? = null
        get() = field ?: ""

    open fun isSuccess(): Boolean {
        return status == MiscValueConst.BOOLEAN_TRUE_STR
    }
}