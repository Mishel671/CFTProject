package ru.michaeldzyuba.cftproject.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.michaeldzyuba.cftproject.ValuteApp
import ru.michaeldzyuba.cftproject.presentation.ConvertValuteFragment
import ru.michaeldzyuba.cftproject.presentation.ListOfValuteFragment


@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
        WorkerModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fragment: ListOfValuteFragment)

    fun inject(fragment: ConvertValuteFragment)

    fun inject(application: ValuteApp)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}