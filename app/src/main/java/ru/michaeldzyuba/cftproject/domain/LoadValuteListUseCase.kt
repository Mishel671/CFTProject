package ru.michaeldzyuba.cftproject.domain

import javax.inject.Inject

class LoadValuteListUseCase @Inject constructor(
    private val repository: ValuteRepository
) {
    operator fun invoke() = repository.loadValuteList()
}