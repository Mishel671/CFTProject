package ru.michaeldzyuba.cftproject.domain

data class ValuteItem (
    val id: Int,
    val uid: String,
    val charCode:String,
    val nominal:Int,
    val name: String,
    val value: Double
)