package com.example.ulkelermvvm.service

import com.example.ulkelermvvm.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {

    @GET("master/countrydataset.json")
    fun getCountry():Single<List<Country>>
}