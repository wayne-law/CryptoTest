package com.wayne.crypto.test.component

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.wayne.crypto.test.module.wallet.business.WalletTabViewModel

/**
 * Created by wayne.liu on 2020/9/9
 */
class AppViewModelFactory(private val appContainer: AppContainer,
                          owner: SavedStateRegistryOwner,
                          defaultArgs: Bundle?) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        return when (modelClass) {
            WalletTabViewModel::class.java -> {
                WalletTabViewModel(
                    savedStateHandle = handle,
                    positionService = appContainer.positionService,
                    repository = appContainer.walletTabRepository
                ) as T
            }
            else -> {
                throw IllegalArgumentException()
            }
        }

    }
}