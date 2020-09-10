package com.wayne.crypto.test.module.wallet.model

/**
 * Created by wayne.liu on 2020/9/9
 */
data class CurrencyDetailModel(val coinId: String,
                               val symbol: String,
                               val name: String,
                               val iconImgUrl: String,
                               val displayDecimal: Int)