package com.wayne.crypto.test.module.wallet.model

import java.io.Serializable
import java.math.BigDecimal

/**
 * Created by wayne.liu on 2020/9/9
 */
data class WalletCurrencyModel(val iconUrl: String,
                               val coinName: String,
                               val coinSymbol: String,
                               val balance: BigDecimal,
                               val balanceInUSD: BigDecimal): Serializable