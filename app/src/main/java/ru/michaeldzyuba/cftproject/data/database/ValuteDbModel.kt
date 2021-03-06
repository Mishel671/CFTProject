package ru.michaeldzyuba.cftproject.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "valute_list")
data class ValuteDbModel(
    @PrimaryKey
    val uid: String,
    val charCode: String,
    val nominal: Int,
    val name:String,
    val value: Double
)