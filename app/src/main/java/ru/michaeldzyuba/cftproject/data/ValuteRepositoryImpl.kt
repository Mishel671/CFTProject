package ru.michaeldzyuba.cftproject.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ru.michaeldzyuba.cftproject.data.database.AppDatabase
import ru.michaeldzyuba.cftproject.data.mapper.ValuteMapper
import ru.michaeldzyuba.cftproject.data.network.ApiFactory
import ru.michaeldzyuba.cftproject.data.network.model.DailyResponseDto
import ru.michaeldzyuba.cftproject.data.network.model.ValuteDto
import ru.michaeldzyuba.cftproject.domain.ValuteItem
import ru.michaeldzyuba.cftproject.domain.ValuteRepository

class ValuteRepositoryImpl(private val application: Application):ValuteRepository {

    private val apiService = ApiFactory.apiService
    private val valuteDao = AppDatabase.getInstance(application).valuteDao()
    private val mapper = ValuteMapper()

    override suspend fun loadValuteList() {
        try {
            val dailyResponseDto = apiService.getValuteList()
            val valuteListDto = ArrayList<ValuteDto>(dailyResponseDto.valute?.values)
            val valuteListDbModel = valuteListDto.map{
                mapper.mapDtoToDbModel(it)
            }
            valuteDao.insertValuteList(valuteListDbModel)
        } catch (e: Exception){
        }
    }


    override fun getValuteList(): LiveData<List<ValuteItem>> {
        return Transformations.map(valuteDao.getValuteList()){listDb ->
            listDb.map{
                mapper.mapDbToEntity(it)
            }
        }
    }
}