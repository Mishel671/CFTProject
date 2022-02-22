package ru.michaeldzyuba.cftproject.data.network.model

import com.google.gson.annotations.SerializedName

data class ValuteDto(
    @SerializedName("ID")
    val idValute: String,
    @SerializedName("NumCode")
    val numCode: String?,
    @SerializedName("CharCode")
    val charCode: String,
    @SerializedName("Nominal")
    val nominal: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Value")
    val value: Double,
    @SerializedName("Previous")
    val previous: Double?
)