package com.example.theweatherapp.service

import com.example.theweatherapp.model.City
import com.example.theweatherapp.model.WeatherRepo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherAPI {
    @GET("api/location/search/")
        fun getCities(@Query("lattlong") lattlong:String): Single<ArrayList<City>>
    @GET("api/location/{woeid}/")
    fun getWeather(@Path("woeid") woeid:Int): Single<WeatherRepo>

}