package ru.michaeldzyuba.cftproject.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.michaeldzyuba.cftproject.domain.ValuteItem
import javax.inject.Inject

class ConvertValuteViewModel @Inject constructor() : ViewModel() {

    private val _errorInputRubles = MutableLiveData<Boolean>()
    val errorInputRubles: LiveData<Boolean>
        get() = _errorInputRubles

    private val _resultValute = MutableLiveData<Double>()
    val resultValute: LiveData<Double>
        get() = _resultValute

    fun convertValute(inputValue: String?, valuteCost: Double) {
        val rubles = parseValue(inputValue)
        val fieldValid = validateInput(rubles)
        if (fieldValid) {
            _resultValute.value = rubles / valuteCost
        }
    }

    private fun parseValue(inputCount: String?): Double {
        return try {
            inputCount?.trim()?.toDouble() ?: 0.0
        } catch (e: Exception) {
            0.0
        }
    }

    private fun validateInput(rubles: Double): Boolean {
        var result = true
        if (rubles == 0.0) {
            _errorInputRubles.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputRubles() {
        _errorInputRubles.value = false
    }
}