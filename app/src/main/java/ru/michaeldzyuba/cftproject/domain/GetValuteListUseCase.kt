package ru.michaeldzyuba.cftproject.domain

class GetValuteListUseCase(private val repository: ValuteRepository) {

    operator fun invoke() = repository.getValuteList()
}