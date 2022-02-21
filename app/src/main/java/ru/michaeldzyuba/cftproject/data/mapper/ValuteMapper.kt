package ru.michaeldzyuba.cftproject.data.mapper

import ru.michaeldzyuba.cftproject.data.database.ValuteDbModel
import ru.michaeldzyuba.cftproject.data.network.model.ValuteDto
import ru.michaeldzyuba.cftproject.domain.ValuteItem

class ValuteMapper {

    fun mapDtoToDbModel(dto: ValuteDto) = ValuteDbModel(
        uid = dto.idValute,
        charCode = dto.charCode,
        nominal = dto.nominal,
        name = dto.name,
        value = dto.value
    )

    fun mapDbToEntity(db: ValuteDbModel) = ValuteItem(
        id = db.id,
        uid = db.uid,
        charCode = db.charCode,
        nominal = db.nominal,
        name = db.name,
        value = db.value
    )
}