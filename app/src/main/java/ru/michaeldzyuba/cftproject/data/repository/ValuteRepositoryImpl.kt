package ru.michaeldzyuba.cftproject.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import ru.michaeldzyuba.cftproject.data.database.AppDatabase
import ru.michaeldzyuba.cftproject.data.database.ValuteDao
import ru.michaeldzyuba.cftproject.data.mapper.ValuteMapper
import ru.michaeldzyuba.cftproject.data.network.ApiFactory
import ru.michaeldzyuba.cftproject.data.network.model.ValuteDto
import ru.michaeldzyuba.cftproject.data.workers.UpdateDataWorker
import ru.michaeldzyuba.cftproject.domain.ValuteItem
import ru.michaeldzyuba.cftproject.domain.ValuteRepository
import javax.inject.Inject

class ValuteRepositoryImpl @Inject constructor(
    private val application: Application,
    private val valuteDao: ValuteDao,
    private val mapper: ValuteMapper
) : ValuteRepository {

    override fun loadValuteList() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            UpdateDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            UpdateDataWorker.makeRequest()
        )
    }


    override fun getValuteList(): LiveData<List<ValuteItem>> {
        return Transformations.map(valuteDao.getValuteList()) { listDb ->
            listDb.map {
                mapper.mapDbToEntity(it)
            }
        }
    }
}