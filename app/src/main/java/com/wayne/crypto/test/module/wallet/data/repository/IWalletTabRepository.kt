package com.wayne.crypto.test.module.wallet.data.repository

import com.wayne.crypto.test.module.wallet.data.net.entity.CurrencyRatesResp
import com.wayne.crypto.test.module.wallet.data.net.entity.CurrenciesResp

/**
 * Created by wayne.liu on 2020/9/9
 */
interface IWalletTabRepository {

    suspend fun getCurrenciesList(): CurrenciesResp

    suspend fun getCurrencyRates(): CurrencyRatesResp
}