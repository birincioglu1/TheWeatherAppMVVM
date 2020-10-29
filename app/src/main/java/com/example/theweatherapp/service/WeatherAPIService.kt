package com.example.theweatherapp.service

import com.example.theweatherapp.model.City
import com.example.theweatherapp.model.WeatherRepo
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherAPIService {
    private val BASE_URL = "https://www.metaweather.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(WeatherAPI::class.java)

    fun getCities(lattlong: String): Single<ArrayList<City>> {
        return api.getCities(lattlong)
    }
    fun getWeatherDetail(woeid: Int): Single<WeatherRepo> {
        return api.getWeather(woeid)
    }
}