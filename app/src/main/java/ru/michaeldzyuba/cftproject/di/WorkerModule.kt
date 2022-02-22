package ru.michaeldzyuba.cftproject.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.michaeldzyuba.cftproject.data.workers.ChildWorkerFactory
import ru.michaeldzyuba.cftproject.data.workers.UpdateDataWorker

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(UpdateDataWorker::class)
    fun bindUpdateDataWorker(worker:UpdateDataWorker.Factory): ChildWorkerFactory
}