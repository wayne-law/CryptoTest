package com.wayne.crypto.test.module.wallet.data.net.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by wayne.liu on 2020/9/9
 */
class CurrencyRatesResp(@SerializedName("warning") val warning: String? = null,
                        @SerializedName("tiers") val tiers: List<TierData?>? = null): BaseResponse() {

    class TierData(@SerializedName("from_currency") val fromCurrency: String? = null,
                   @SerializedName("to_currency") val toCurrency: String? = null,
                   @SerializedName("rates") val rates: List<RateData?>? = null)

    class RateData(@SerializedName("amount") val amount: String? = null,
                   @SerializedName("rate") val rate: String? = null)
}