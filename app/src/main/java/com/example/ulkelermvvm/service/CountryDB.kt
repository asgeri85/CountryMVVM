package com.example.ulkelermvvm.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ulkelermvvm.model.Country

@Database(entities = arrayOf(Country::class), version = 1,exportSchema = false )
abstract class CountryDB :RoomDatabase() {
    abstract fun countryDao() :CountryDAO

    companion object{
        @Volatile private var instance:CountryDB?=null

        private val lock=Any()

        operator fun invoke(context:Context)= instance?: synchronized(lock){
            instance?: createDB(context).also {
                instance=it
            }
        }

        private fun createDB(context: Context)=Room.databaseBuilder(
            context.applicationContext,CountryDB::class.java,"countrydatabase"
        ).allowMainThreadQueries().build()
    }
}