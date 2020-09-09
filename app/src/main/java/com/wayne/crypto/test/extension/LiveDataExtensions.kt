package com.wayne.crypto.test.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Created by wayne.liu on 2020/8/11
 */
@Suppress("detekt.UnsafeCast")
fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>