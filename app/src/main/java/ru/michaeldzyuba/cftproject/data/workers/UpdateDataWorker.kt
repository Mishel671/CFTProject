package ru.michaeldzyuba.cftproject.data.workers

import android.content.Context
import androidx.work.*
import kotlinx.coroutines.delay
import ru.michaeldzyuba.cftproject.data.database.AppDatabase
import ru.michaeldzyuba.cftproject.data.database.ValuteDao
import ru.michaeldzyuba.cftproject.data.mapper.ValuteMapper
import ru.michaeldzyuba.cftproject.data.network.ApiFactory
import ru.michaeldzyuba.cftproject.data.network.ApiService
import ru.michaeldzyuba.cftproject.data.network.model.ValuteDto
import javax.inject.Inject

class UpdateDataWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val apiService: ApiService,
    private val valuteDao: ValuteDao,
    private val mapper: ValuteMapper
) : CoroutineWorker(context, workerParameters) {

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

    class Factory @Inject constructor(
        private val apiService: ApiService,
        private val coinInfoDao: ValuteDao,
        private val mapper: ValuteMapper
    ) : ChildWorkerFactory {

        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return UpdateDataWorker(
                context,
                workerParameters,
                apiService,
                coinInfoDao,
                mapper
            )
        }
    }
}