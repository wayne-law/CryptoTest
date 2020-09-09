package com.wayne.crypto.test.service

import androidx.lifecycle.LiveData
import com.wayne.crypto.test.module.wallet.model.CoinPositionModel

/**
 * Created by wayne.liu on 2020/9/9
 */
interface IPositionService {

    val positionData: LiveData<List<CoinPositionModel>>
}