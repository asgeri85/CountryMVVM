package com.example.ulkelermvvm.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ulkelermvvm.model.Country
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDAO {

    @Insert
     fun insertAll(vararg ulkeler:Country):List<Long>

    @Query("SELECT * FROM country")
     fun getAllCountry(): List<Country>

    @Query("SELECT * FROM country WHERE uuid=:cid")
     fun getCountry(cid:Int):Country

    @Query("DELETE FROM country")
     fun deleteAll()
}