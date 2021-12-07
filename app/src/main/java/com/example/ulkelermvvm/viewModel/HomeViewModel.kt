package com.example.ulkelermvvm.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ulkelermvvm.model.Country
import com.example.ulkelermvvm.service.ApiUtis
import com.example.ulkelermvvm.service.CountryAPI
import com.example.ulkelermvvm.service.CountryDB
import com.example.ulkelermvvm.util.CustomSP
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application):BaseViewModel(application) {
    val countries=MutableLiveData<List<Country>>()
    val countryError=MutableLiveData<Boolean>()
    val countryLoading=MutableLiveData<Boolean>()
    private val API:CountryAPI=ApiUtis.getCountryDAO()
    private val disposable=CompositeDisposable()
    private val customerSp=CustomSP(getApplication())
    private var refTime=10  *60*1000*1000*1000L

    fun refreshData(){
        val updateTime=customerSp.getTime()
        if(updateTime!=null && updateTime!=0L && System.nanoTime() - updateTime<refTime){
            getDataSQLite()
        }else{
            getDataAPI()
        }
    }

    fun getDataAPI(){
        countryLoading.value=true

        disposable.add(
            API.getCountry().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                        storeSQLite(t)
                        Toast.makeText(getApplication(),"APİ veri",Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable) {
                        countryError.value=true
                        countryLoading.value=false
                    }
                })
        )
    }

    private fun storeSQLite(list: List<Country>){
        launch {
            val dao=CountryDB(getApplication()).countryDao()
            dao.deleteAll()
            val listLong=dao.insertAll(*list.toTypedArray())
            var i=0
            while (i<listLong.size){
                list[i].uuid=listLong[i].toInt()
                i++
            }
            countries.value=list
            countryLoading.value=false
            countryError.value=false
        }

        customerSp.saveTime(System.nanoTime())
    }

    private fun getDataSQLite(){
        countryLoading.value=true
        launch {
            val list=CountryDB(getApplication()).countryDao().getAllCountry()
            countries.value=list
            countryLoading.value=false
            countryError.value=false
            Toast.makeText(getApplication(),"SQL veri",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

 }