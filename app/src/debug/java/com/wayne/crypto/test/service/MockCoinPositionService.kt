package com.wayne.crypto.test.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wayne.crypto.test.constant.CoinConst
import com.wayne.crypto.test.extension.asLiveData
import com.wayne.crypto.test.module.wallet.model.CoinPositionModel
import java.math.BigDecimal

/**
 * Created by wayne.liu on 2020/9/9
 */
class MockCoinPositionService : IPositionService {

    private val positionMutableData: MutableLiveData<List<CoinPositionModel>> = MutableLiveData<List<CoinPositionModel>>().apply {
        value = listOf(
            CoinPositionModel(CoinConst.COIN_BTC, BigDecimal("1.5123")),
            CoinPositionModel(CoinConst.COIN_ETH, BigDecimal("22.4123")),
            CoinPositionModel(CoinConst.COIN_CRO, BigDecimal("1717.8765"))
        )
    }
    override val positionData: LiveData<List<CoinPositionModel>>

    init {
        positionData = positionMutableData.asLiveData()
    }
}