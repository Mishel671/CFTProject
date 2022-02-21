package ru.michaeldzyuba.cftproject.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import ru.michaeldzyuba.cftproject.data.database.AppDatabase
import ru.michaeldzyuba.cftproject.data.mapper.ValuteMapper
import ru.michaeldzyuba.cftproject.data.network.ApiFactory
import ru.michaeldzyuba.cftproject.data.network.model.ValuteDto

class UpdateDataWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    private val apiService = ApiFactory.apiService
    private val valuteDao = AppDatabase.getInstance(context).valuteDao()
    private val mapper = ValuteMapper()


    override suspend fun doWork(): Result {
        while(true){
            try {
                val dailyResponseDto = apiService.getValuteList()
                val valuteListDto = ArrayList<ValuteDto>(dailyResponseDto.valute?.values)
                val valuteListDbModel = valuteListDto.map{
                    mapper.mapDtoToDbModel(it)
                }
                valuteDao.insertValuteList(valuteListDbModel)
            } catch (e: Exception){
            }
            delay(20000)
        }
    }

    companion object {
        const val NAME = "UpdateDataWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<UpdateDataWorker>().build()
        }
    }
}