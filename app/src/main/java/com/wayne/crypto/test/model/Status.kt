package com.wayne.crypto.test.model

/**
 * Created by Wayne.Liu on 2019/11/20.
 */
sealed class Status {
    object Success : Status()
    data class Failed(val error: Throwable) : Status()
    object Loading : Status()
}