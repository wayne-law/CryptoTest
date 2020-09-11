package com.wayne.crypto.test.component

import android.app.Application

/**
 * Created by wayne.liu on 2020/9/11
 */
open class CryptoApplication: Application() {

    open val appContainer: AppContainer = AppContainer()
}