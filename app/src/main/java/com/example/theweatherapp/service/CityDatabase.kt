package com.example.theweatherapp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.theweatherapp.model.City


@Database(entities = arrayOf(City::class),version = 1,exportSchema = false)
abstract class CityDatabase:RoomDatabase() {
    abstract fun cityDao():CityDAO

    companion object
    {
        //different threads can use
       @Volatile private var instace:CityDatabase?=null

        private val lock=Any()

        operator fun invoke(context: Context)= instace ?: synchronized(lock){
            instace ?: createDatabase(context).also {
                instace=it
            }
        }
        private fun createDatabase(context: Context)=Room.databaseBuilder(
            context.applicationContext,CityDatabase::class.java,"citydatabase"
        ).allowMainThreadQueries().build()
    }
}