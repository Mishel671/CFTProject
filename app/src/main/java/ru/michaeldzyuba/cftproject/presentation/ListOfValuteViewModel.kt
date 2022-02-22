package ru.michaeldzyuba.cftproject.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.michaeldzyuba.cftproject.domain.GetValuteListUseCase
import ru.michaeldzyuba.cftproject.domain.LoadValuteListUseCase
import javax.inject.Inject

class ListOfValuteViewModel @Inject constructor(
    private val getValuteListUseCase: GetValuteListUseCase,
    private val loadValuteListUseCase: LoadValuteListUseCase,
    private val application: Application
) : ViewModel() {

    val valuteList = getValuteListUseCase()

    private val _internetToast = MutableLiveData<Boolean>()
    val internetToast: LiveData<Boolean>
        get() = _internetToast

    init {
        loadData()
    }

    fun loadData() {
        if (isOnline()) {
            viewModelScope.launch {
                loadValuteListUseCase()
            }
        } else {
            _internetToast.value = true
        }
    }


    private fun isOnline(): Boolean {
        val connectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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

    fun resetInternetToast(){
        _internetToast.value = false
    }
}