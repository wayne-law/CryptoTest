package com.wayne.crypto.test.debug.component

import com.wayne.crypto.test.component.AppContainer
import com.wayne.crypto.test.component.CryptoApplication

/**
 * Created by wayne.liu on 2020/9/11
 */
class DebugCryptoApplication: CryptoApplication() {

    override val appContainer: AppContainer = MockAppContainer(this)
}