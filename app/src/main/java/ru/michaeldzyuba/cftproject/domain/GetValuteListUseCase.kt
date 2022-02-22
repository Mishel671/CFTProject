package ru.michaeldzyuba.cftproject.domain

import javax.inject.Inject

class GetValuteListUseCase @Inject constructor(
    private val repository: ValuteRepository
) {

    operator fun invoke() = repository.getValuteList()
}