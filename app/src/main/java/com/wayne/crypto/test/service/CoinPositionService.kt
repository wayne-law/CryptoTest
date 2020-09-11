package com.wayne.crypto.test.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wayne.crypto.test.extension.asLiveData
import com.wayne.crypto.test.module.wallet.model.CoinPositionModel

/**
 * Created by wayne.liu on 2020/9/11
 */
class CoinPositionService : IPositionService {

    private val positionMutableData: MutableLiveData<List<CoinPositionModel>> = MutableLiveData()
    override val positionData: LiveData<List<CoinPositionModel>>

    init {
        positionData = positionMutableData.asLiveData()
    }
}