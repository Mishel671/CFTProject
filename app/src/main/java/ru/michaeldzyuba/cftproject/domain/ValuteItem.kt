package ru.michaeldzyuba.cftproject.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ValuteItem(
    val uid: String,
    val charCode: String,
    val nominal: Int,
    val name: String,
    val value: Double
) : Parcelable