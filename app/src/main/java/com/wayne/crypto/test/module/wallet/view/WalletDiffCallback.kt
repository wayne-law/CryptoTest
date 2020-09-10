package com.wayne.crypto.test.module.wallet.view

import androidx.recyclerview.widget.DiffUtil
import com.wayne.crypto.test.module.wallet.model.WalletCurrencyModel

/**
 * Created by wayne.liu on 2020/9/10
 */
class WalletDiffCallback : DiffUtil.ItemCallback<Any>() {

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if (oldItem is WalletCurrencyModel && newItem is WalletCurrencyModel) {
            oldItem.coinId == newItem.coinId
        } else {
            //TODO 补充其他数据类型时的比较
            false
        }
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        //TODO 补充其他数据类型时的比较
        return (oldItem as? WalletCurrencyModel)?.equals(newItem) ?: false
    }
}