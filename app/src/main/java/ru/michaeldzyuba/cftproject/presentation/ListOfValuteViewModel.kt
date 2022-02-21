package ru.michaeldzyuba.cftproject.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.michaeldzyuba.cftproject.data.ValuteRepositoryImpl
import ru.michaeldzyuba.cftproject.domain.GetValuteListUseCase
import ru.michaeldzyuba.cftproject.domain.LoadValuteListUseCase

class ListOfValuteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ValuteRepositoryImpl(application)

    private val loadValuteListUseCase = LoadValuteListUseCase(repository)
    private val getValuteListUseCase = GetValuteListUseCase(repository)

    val valuteList = getValuteListUseCase()

    private val _toastInternet = MutableLiveData<Boolean>()
    val toastInternet: LiveData<Boolean>
        get() = _toastInternet

    init {
        loadData()
    }

    fun loadData() {
        if (isOnline()) {
            viewModelScope.launch {
                loadValuteListUseCase()
            }
        } else {
            _toastInternet.value = true
            _toastInternet.value = false
        }
    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            getApplication<Application>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ->
                        return true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->
                        return true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ->
                        return true
                }
            }
        }
        return false
    }


}