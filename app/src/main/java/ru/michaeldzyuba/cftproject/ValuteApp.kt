package ru.michaeldzyuba.cftproject

import android.app.Application
import androidx.work.Configuration
import ru.michaeldzyuba.cftproject.data.workers.ValuteWorkerFactory
import ru.michaeldzyuba.cftproject.di.DaggerApplicationComponent
import javax.inject.Inject

class ValuteApp:Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: ValuteWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }


    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}