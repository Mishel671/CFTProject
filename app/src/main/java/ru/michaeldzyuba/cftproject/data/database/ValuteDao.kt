package ru.michaeldzyuba.cftproject.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ValuteDao {
    @Query("SELECT * FROM valute_list ORDER BY id")
    fun getValuteList(): LiveData<List<ValuteDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertValuteList(valuteList: List<ValuteDbModel>)
}