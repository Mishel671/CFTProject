package ru.michaeldzyuba.cftproject.data.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class DailyResponseDto(
    @SerializedName("Date")
    val date: String?,
    @SerializedName("PreviousDate")
    val previousDate:String?,
    @SerializedName("PreviousURL")
    val previousURL:String?,
    @SerializedName("Timestamp")
    val timestamp:String?,
    @SerializedName("Valute")
    val valute: TreeMap<String, ValuteDto>?
)