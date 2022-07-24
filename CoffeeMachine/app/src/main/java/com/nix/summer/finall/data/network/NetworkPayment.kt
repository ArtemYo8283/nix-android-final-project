package com.nix.summer.finall.data.network

import com.google.gson.annotations.SerializedName

data class NetworkPayment(
    val result: String,
    val documentation: String,
    @SerializedName("terms_of_use") val termsOfUse: String,
    @SerializedName("time_last_update_unix") val timeLastUpdateUNIX: String,
    @SerializedName("time_last_update_utc") val lastUpdate: String,
    @SerializedName("time_next_update_unix") val timeNextUpdateUNIX: String,
    @SerializedName("time_next_update_utc") val nextUpdate: String,
    @SerializedName("base_code") val baseCurrency: String,
    @SerializedName("target_code") val targetCurrency: String,
    @SerializedName("conversion_rate") val conversionRate: String,
    @SerializedName("conversion_result") val conversionResult: String
)
