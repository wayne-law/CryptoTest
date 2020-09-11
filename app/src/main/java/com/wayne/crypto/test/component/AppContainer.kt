package com.wayne.crypto.test.component

import com.wayne.crypto.test.module.wallet.data.repository.IWalletTabRepository
import com.wayne.crypto.test.module.wallet.data.repository.WalletTabRepositoryImpl
import com.wayne.crypto.test.service.CoinPositionService
import com.wayne.crypto.test.service.IPositionService

/**
 * Created by wayne.liu on 2020/9/11
 */
open class AppContainer {

    open val positionService: IPositionService = CoinPositionService()
    open val walletTabRepository: IWalletTabRepository = WalletTabRepositoryImpl()
}