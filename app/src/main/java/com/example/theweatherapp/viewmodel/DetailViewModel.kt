package com.example.theweatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.theweatherapp.model.Weather
import com.example.theweatherapp.model.WeatherRepo
import com.example.theweatherapp.service.WeatherAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class DetailViewModel:ViewModel() {

    val weathers= MutableLiveData<WeatherRepo>()
    val weatherErr= MutableLiveData<Boolean>()
    val weatherLoading= MutableLiveData<Boolean>()

    private val weatherAPIService= WeatherAPIService()
    private val disposable= CompositeDisposable()
    fun refrehData(id:Int)
    {
        getDataFromAPI(id)
    }

    private fun getDataFromAPI(s: Int) {
        weatherLoading.value=false
        disposable.add(
            weatherAPIService.getWeatherDetail(s)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeatherRepo>(){
                    override fun onSuccess(t: WeatherRepo) {
                        weathers.value=t
                        weatherErr.value=false
                        weatherLoading.value=false
                    }

                    override fun onError(e: Throwable) {
                        weatherErr.value=true
                        weatherLoading.value=false
                        e.printStackTrace()
                    }

                })
        )
    }
}