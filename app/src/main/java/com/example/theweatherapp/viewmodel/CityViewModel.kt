package com.example.theweatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.theweatherapp.model.City
import com.example.theweatherapp.service.WeatherAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CityViewModel:ViewModel() {


    val cities= MutableLiveData<ArrayList<City>>()
    val cityErr=MutableLiveData<Boolean>()
    val cityLoading=MutableLiveData<Boolean>()
    private val weatherAPIService=WeatherAPIService()
    private val disposable=CompositeDisposable()

    fun refrehData()
    {
        getDataFromAPI("36.96,-122.02")
    }

    private fun getDataFromAPI(latlong:String) {
        cityLoading.value=true
        disposable.add(
            weatherAPIService.getCities(latlong)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<ArrayList<City>>(){
                    override fun onSuccess(t: ArrayList<City>) {
                        cities.value=t
                        cityErr.value=false
                        cityLoading.value=false
                        Log.e("HEY","api calisti")
                    }

                    override fun onError(e: Throwable) {
                        cityLoading.value=false
                        cityErr.value=true
                        Log.e("HEY","api calismadi"+e.message)
                        e.printStackTrace()

                    }

                })
        )
    }
}