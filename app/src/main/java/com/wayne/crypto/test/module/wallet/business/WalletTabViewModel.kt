package com.wayne.crypto.test.module.wallet.business

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wayne.crypto.test.constant.MiscValueConst
import com.wayne.crypto.test.extension.asLiveData
import com.wayne.crypto.test.model.Status
import com.wayne.crypto.test.module.wallet.data.repository.IWalletTabRepository
import com.wayne.crypto.test.module.wallet.model.CoinPositionModel
import com.wayne.crypto.test.module.wallet.model.CurrencyDetailModel
import com.wayne.crypto.test.module.wallet.model.CurrencyTierModel
import com.wayne.crypto.test.module.wallet.model.WalletCurrencyModel
import com.wayne.crypto.test.service.IPositionService
import com.wayne.crypto.test.utils.NumberParserUtil
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Created by wayne.liu on 2020/9/9
 */
class WalletTabViewModel constructor(
    savedStateHandle: SavedStateHandle,
    private val positionService: IPositionService,
    private val repository: IWalletTabRepository
) : ViewModel() {

    companion object {
        private const val TAG = "WalletTabViewModel"
        private const val KEY_WALLET_CURRENCY = "key_wallet_currency"
    }

    private val pageStatusMutableData: MutableLiveData<Status> = MutableLiveData()
    val pageStatusData = pageStatusMutableData.asLiveData()
    private val walletCurrenciesMutableData: MutableLiveData<List<WalletCurrencyModel>>
    val pageData: LiveData<List<Any>>

    init {
        walletCurrenciesMutableData = savedStateHandle.getLiveData(KEY_WALLET_CURRENCY)
        pageData = Transformations.map(walletCurrenciesMutableData) { it }
        //TODO 补充对持币变化时的处理

    }

    fun requestData() {
        if (pageStatusMutableData.value == Status.Loading) {
            return
        }
        pageStatusMutableData.value = Status.Loading
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            pageStatusMutableData.value = Status.Failed(throwable)
            Log.e(TAG, "request wallet data failed, error = $throwable")
        }) {
            val positionCoins = positionService.positionData.value
            if (positionCoins.isNullOrEmpty()) {
                pageStatusMutableData.value = Status.Success
                return@launch
            }
            Log.i(TAG, "start request wallet data")
            walletCurrenciesMutableData.value = performRequest(positionCoins)
            pageStatusMutableData.value = Status.Success
            Log.i(TAG, "request wallet data success")
        }
    }

    private suspend fun performRequest(positionCoins: List<CoinPositionModel>): List<WalletCurrencyModel> {
        return withContext(Dispatchers.Default) {
            //获取货币数据
            val currenciesDeferred = async { doRequestCurrencies() }
            //获取货币比率数据
            val currencyRateDeferred = async { doRequestCurrencyRates() }
            val currenciesMap = currenciesDeferred.await()
            val currencyRateMap = currencyRateDeferred.await()
            return@withContext positionCoins.map {
                val positionAmount = it.amount
                val currencyDetail = currenciesMap[it.coinId]
                var currencyRate = BigDecimal.ZERO
                //货币比率数据中与持仓数量最接近的数量值
                var positionCeilAmount = BigDecimal(Double.MAX_VALUE)
                //筛选持仓数量对应的货币比率
                currencyRateMap[it.coinId]?.rates?.forEach { (amount, rate) ->
                    if (amount >= positionAmount && amount < positionCeilAmount) {
                        positionCeilAmount = amount
                        currencyRate = rate
                    }
                }
                val balance = currencyDetail?.displayDecimal?.let { scale ->
                    positionAmount.setScale(scale, RoundingMode.FLOOR)
                } ?: positionAmount
                //计算以USD计价的余额
                val balanceInUSD = positionAmount.multiply(currencyRate).setScale(MiscValueConst.DECIMAL_SCALE_IN_USD, RoundingMode.FLOOR)
                return@map WalletCurrencyModel(
                    coinId = it.coinId,
                    iconUrl = currencyDetail?.iconImgUrl.orEmpty(),
                    coinName = currencyDetail?.name.orEmpty(),
                    coinSymbol = currencyDetail?.symbol.orEmpty(),
                    balance = balance,
                    balanceInUSD = balanceInUSD
                )
            }
        }
    }

    private suspend fun doRequestCurrencies(): Map<String, CurrencyDetailModel> {
        return repository.getCurrenciesList().currencies?.asSequence()?.mapNotNull { currencyData ->
            currencyData?.let {
                CurrencyDetailModel(
                    coinId = it.coinId.orEmpty(),
                    symbol = it.symbol.orEmpty(),
                    name = it.name.orEmpty(),
                    iconImgUrl = it.colorfulImgUrl.orEmpty(),
                    displayDecimal = NumberParserUtil.parseInt(it.displayDecimal, MiscValueConst.DEFAULT_INT_VALUE)
                )
            }
        }?.associateBy { it.coinId } ?: emptyMap()
    }

    private suspend fun doRequestCurrencyRates(): Map<String, CurrencyTierModel> {
        return repository.getCurrencyRates().tiers?.asSequence()?.mapNotNull { tierData ->
            tierData?.let {
                CurrencyTierModel(
                    fromCurrency = tierData.fromCurrency.orEmpty(),
                    toCurrency = tierData.toCurrency.orEmpty(),
                    rates = tierData.rates?.asSequence()?.filterNotNull()?.associate { rateData ->
                        NumberParserUtil.parseBigDecimal(rateData.amount, BigDecimal.ZERO) to NumberParserUtil.parseBigDecimal(rateData.rate, BigDecimal.ZERO)
                    } ?: emptyMap()
                )
            }
        }?.associateBy { it.fromCurrency } ?: emptyMap()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    suspend fun testRequestData(positionCoins: List<CoinPositionModel>) = performRequest(positionCoins)

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    suspend fun testRequestCurrencies() = doRequestCurrencies()

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    suspend fun testRequestCurrencyRates() = doRequestCurrencyRates()
}