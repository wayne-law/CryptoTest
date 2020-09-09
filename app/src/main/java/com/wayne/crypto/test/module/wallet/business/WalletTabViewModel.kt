package com.wayne.crypto.test.module.wallet.business

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wayne.crypto.test.extension.asLiveData
import com.wayne.crypto.test.model.Status
import com.wayne.crypto.test.module.wallet.data.repository.IWalletTabRepository
import com.wayne.crypto.test.module.wallet.model.CoinPositionModel
import com.wayne.crypto.test.module.wallet.model.WalletCurrencyModel
import com.wayne.crypto.test.service.IPositionService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
    }

    private val pageStatusMutableData: MutableLiveData<Status> = MutableLiveData()
    val pageStatusData = pageStatusMutableData.asLiveData()
    private val walletCurrenciesMutableData: MutableLiveData<List<WalletCurrencyModel>> = MutableLiveData()
    val pageData: LiveData<List<Any>> = Transformations.map(walletCurrenciesMutableData) { it }

    fun requestData() {
        if (pageStatusMutableData.value == Status.Loading) {
            return
        }
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
            emptyList()
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    suspend fun testRequestData(positionCoins: List<CoinPositionModel>) = performRequest(positionCoins)
}