package ru.michaeldzyuba.cftproject.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.michaeldzyuba.cftproject.presentation.ConvertValuteViewModel
import ru.michaeldzyuba.cftproject.presentation.ListOfValuteViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListOfValuteViewModel::class)
    fun bindListOfValuteViewModel(viewModel: ListOfValuteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ConvertValuteViewModel::class)
    fun bindConvertValuteViewModel(viewModel: ConvertValuteViewModel): ViewModel
}