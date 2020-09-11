package com.wayne.crypto.test.debug.component

import android.app.Application
import com.wayne.crypto.test.component.AppContainer
import com.wayne.crypto.test.debug.module.wallet.data.repository.MockWalletTabRepository
import com.wayne.crypto.test.debug.service.MockCoinPositionService
import com.wayne.crypto.test.module.wallet.data.repository.IWalletTabRepository
import com.wayne.crypto.test.service.IPositionService

/**
 * Created by wayne.liu on 2020/9/11
 */
class MockAppContainer(application: Application): AppContainer() {

    override val positionService: IPositionService = MockCoinPositionService()
    override val walletTabRepository: IWalletTabRepository = MockWalletTabRepository(application)
}