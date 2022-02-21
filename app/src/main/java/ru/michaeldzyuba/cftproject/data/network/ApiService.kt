package ru.michaeldzyuba.cftproject.data.network

import retrofit2.http.GET
import ru.michaeldzyuba.cftproject.data.network.model.DailyResponseDto

interface ApiService {

    @GET(VALUTE_LIST)
    suspend fun getValuteList(): DailyResponseDto

    companion object{
        private const val VALUTE_LIST = "daily_json.js"
    }
}