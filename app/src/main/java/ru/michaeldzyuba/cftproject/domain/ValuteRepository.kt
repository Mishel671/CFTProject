package ru.michaeldzyuba.cftproject.domain

import androidx.lifecycle.LiveData


interface ValuteRepository {

    suspend fun loadValuteList()

    fun getValuteList():LiveData<List<ValuteItem>>
}