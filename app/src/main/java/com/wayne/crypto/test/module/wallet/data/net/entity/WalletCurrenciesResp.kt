package com.wayne.crypto.test.module.wallet.data.net.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by wayne.liu on 2020/9/9
 */
class WalletCurrenciesResp(@SerializedName("total") val total: String? = null,
                           @SerializedName("currencies") val currencies: List<CurrencyData?>? = null): BaseResponse() {

    class CurrencyData(@SerializedName("coin_id") val coinId: String? = null,
                       @SerializedName("name") val name: String? = null,
                       @SerializedName("symbol") val symbol: String? = null,
                       @SerializedName("token_decimal") val tokenDecimal: String? = null,
                       @SerializedName("contract_address") val contractAddress: String? = null,
                       @SerializedName("withdrawal_eta") val withdrawalEta: List<String?>? = null,
                       @SerializedName("colorful_image_url") val colorfulImgUrl: String? = null,
                       @SerializedName("gray_image_url") val grayImgUrl: String? = null,
                       @SerializedName("has_deposit_address_tag") val hasDepositAddress: String? = null,
                       @SerializedName("min_balance") val minBalance: String? = null,
                       @SerializedName("blockchain_symbol") val blockChainSymbol: String? = null,
                       @SerializedName("trading_symbol") val tradingSymbol: String? = null,
                       @SerializedName("code") val code: String? = null,
                       @SerializedName("explorer") val explorer: String? = null,
                       @SerializedName("is_erc20") val isErc20: String? = null,
                       @SerializedName("gas_limit") val gasLimit: String? = null,
                       @SerializedName("token_decimal_value") val tokenDecimalValue: String? = null,
                       @SerializedName("display_decimal") val displayDecimal: String? = null,
                       @SerializedName("supports_legacy_address") val supportsLegacyAdd: String? = null,
                       @SerializedName("deposit_address_tag_name") val depositAddTagName: String? = null,
                       @SerializedName("deposit_address_tag_type") val depositAddTagType: String? = null,
                       @SerializedName("num_confirmation_required") val confirmReqNum: String? = null)

}