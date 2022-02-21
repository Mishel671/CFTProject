package ru.michaeldzyuba.cftproject.domain

class LoadValuteListUseCase(private val repository: ValuteRepository) {
    suspend operator fun invoke() = repository.loadValuteList()
}