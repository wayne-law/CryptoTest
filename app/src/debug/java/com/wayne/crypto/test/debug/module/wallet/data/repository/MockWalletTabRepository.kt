package com.wayne.crypto.test.debug.module.wallet.data.repository

import android.app.Application
import android.util.Log
import com.google.gson.GsonBuilder
import com.wayne.crypto.test.module.wallet.data.net.entity.CurrenciesResp
import com.wayne.crypto.test.module.wallet.data.net.entity.CurrencyRatesResp
import com.wayne.crypto.test.module.wallet.data.repository.IWalletTabRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.InputStreamReader
import kotlin.random.Random

/**
 * Created by wayne.liu on 2020/9/9
 */
class MockWalletTabRepository(private val application: Application) : IWalletTabRepository {

    companion object {
        private const val TAG = "MockWalletTabRepository"
        private val gson = GsonBuilder().create()
    }

    override suspend fun getCurrenciesList(): CurrenciesResp {
        return withContext(Dispatchers.IO) {
            Log.i(TAG, "start get currencies list")
            //mock request delay
            delay(500)
            runCatching {
                InputStreamReader(application.assets.open("currenciesList.json")).use { jsonStreamReader ->
                    gson.fromJson(jsonStreamReader, CurrenciesResp::class.java)
                }
            }.onSuccess {
                Log.i(TAG, "get currencies list success")
            }.onFailure {
                Log.i(TAG, "get currencies list failed, error = $it")
            }.getOrThrow()
        }
    }

    override suspend fun getCurrencyRates(): CurrencyRatesResp {
        return withContext(Dispatchers.IO) {
            Log.i(TAG, "start get currency rates")
            //mock request delay
            delay(500)
            runCatching {
                val fileName = "currencyRateList-${Random.nextInt(from = 1, until = 3)}.json"
                InputStreamReader(application.assets.open(fileName)).use { jsonStreamReader ->
                    gson.fromJson(jsonStreamReader, CurrencyRatesResp::class.java)
                }
            }.onSuccess {
                Log.i(TAG, "get currency rates success")
            }.onFailure {
                Log.i(TAG, "get currency rates failed, error = $it")
            }.getOrThrow()
        }
    }
}