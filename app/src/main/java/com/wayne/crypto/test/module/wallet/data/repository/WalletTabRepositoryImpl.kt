package com.wayne.crypto.test.module.wallet.data.repository

import com.wayne.crypto.test.module.wallet.data.net.entity.CurrenciesResp
import com.wayne.crypto.test.module.wallet.data.net.entity.CurrencyRatesResp

/**
 * Created by wayne.liu on 2020/9/11
 */
class WalletTabRepositoryImpl: IWalletTabRepository {

    override suspend fun getCurrenciesList(): CurrenciesResp {
        //TODO 接口调用
        return CurrenciesResp()
    }

    override suspend fun getCurrencyRates(): CurrencyRatesResp {
        //TODO 接口调用
        return CurrencyRatesResp()
    }
}