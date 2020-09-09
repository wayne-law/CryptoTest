package com.wayne.crypto.test.module.wallet.model

import java.math.BigDecimal

/**
 * Created by wayne.liu on 2020/9/9
 */
data class CurrencyTierModel(val fromCurrency: String,
                             val toCurrency: String,
                             val rates: Map<BigDecimal, BigDecimal>)