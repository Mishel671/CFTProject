package ru.michaeldzyuba.cftproject.domain

import androidx.lifecycle.LiveData


interface ValuteRepository {

    fun loadValuteList()

    fun getValuteList():LiveData<List<ValuteItem>>
}