package com.example.theweatherapp.service

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.theweatherapp.model.City

@Dao
interface CityDAO {
    @Insert //suspende coroutine , pause & resume     varang-->single single insert like list    return Long->primary key
    suspend fun insertAll(vararg cities:City):List<Long>

    @Query("SELECT * FROM City")
    fun getAllCities():List<City>

    @Query("DELETE FROM city")
     suspend  fun deleteAllCities()
}