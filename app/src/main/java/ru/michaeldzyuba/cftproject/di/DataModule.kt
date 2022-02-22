package ru.michaeldzyuba.cftproject.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.michaeldzyuba.cftproject.data.database.AppDatabase
import ru.michaeldzyuba.cftproject.data.database.ValuteDao
import ru.michaeldzyuba.cftproject.data.network.ApiFactory
import ru.michaeldzyuba.cftproject.data.network.ApiService
import ru.michaeldzyuba.cftproject.data.repository.ValuteRepositoryImpl
import ru.michaeldzyuba.cftproject.domain.ValuteRepository

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindValuteRepository(impl: ValuteRepositoryImpl): ValuteRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideValuteDao(
            application: Application
        ): ValuteDao {
            return AppDatabase.getInstance(application).valuteDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}