package ru.michaeldzyuba.cftproject.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.michaeldzyuba.cftproject.data.ValuteRepositoryImpl
import ru.michaeldzyuba.cftproject.domain.GetValuteListUseCase
import ru.michaeldzyuba.cftproject.domain.LoadValuteListUseCase

class ListOfValuteViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ValuteRepositoryImpl(application)

    private val loadValuteListUseCase = LoadValuteListUseCase(repository)
    private val getValuteListUseCase = GetValuteListUseCase(repository)

    val valuteList = getValuteListUseCase()

    init {
        viewModelScope.launch {
            loadValuteListUseCase()
        }
    }

}