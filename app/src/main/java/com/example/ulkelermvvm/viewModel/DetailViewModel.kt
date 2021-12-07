package com.example.ulkelermvvm.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.ulkelermvvm.model.Country
import com.example.ulkelermvvm.service.CountryDB
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application){
    val countryLiveData=MutableLiveData<Country>()

    fun getDataRoom(id:Int){
        launch {
            val country=CountryDB(getApplication()).countryDao().getCountry(id)
            countryLiveData.value=country
        }
    }
}