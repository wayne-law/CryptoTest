package com.wayne.crypto.test.component

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.wayne.crypto.test.module.wallet.business.WalletTabViewModel
import com.wayne.crypto.test.module.wallet.data.repository.MockWalletTabRepository
import com.wayne.crypto.test.service.MockCoinPositionService

/**
 * Created by wayne.liu on 2020/9/9
 */
class AppViewModelFactory(val application: Application,
                          owner: SavedStateRegistryOwner,
                          defaultArgs: Bundle?) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return when (modelClass) {
            WalletTabViewModel::class.java -> {
                //TODO replace repository
                WalletTabViewModel(savedStateHandle = handle, positionService = MockCoinPositionService(), repository = MockWalletTabRepository(application)) as T
            }
            else -> {
                throw IllegalArgumentException()
            }
        }

    }
}