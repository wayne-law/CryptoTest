package com.wayne.crypto.test.module.wallet.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.drakeet.multitype.ItemViewBinder
import com.wayne.crypto.test.R
import com.wayne.crypto.test.module.wallet.model.WalletCurrencyModel

/**
 * Created by wayne.liu on 2020/9/10
 */
class WalletCurrencyViewBinder : ItemViewBinder<WalletCurrencyModel, WalletCurrencyViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.layout_wallet_currency_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: WalletCurrencyModel) {
        val context = holder.itemView.context
        Glide.with(context).load(item.iconUrl).into(holder.ivCoinIcon)
        holder.tvCoinName.text = item.coinName
        holder.tvCoinBalance.text = String.format(context.getString(R.string.text_coin_balance_format), item.balance, item.coinSymbol)
        holder.tvCoinBalanceInUSD.text = String.format(context.getString(R.string.text_usd_balance_format), item.balanceInUSD)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivCoinIcon: ImageView = itemView.findViewById(R.id.iv_coin_icon)
        val tvCoinName: TextView = itemView.findViewById(R.id.tv_coin_name)
        val tvCoinBalance: TextView = itemView.findViewById(R.id.tv_coin_balance)
        val tvCoinBalanceInUSD: TextView = itemView.findViewById(R.id.tv_coin_balance_usd)
    }
}