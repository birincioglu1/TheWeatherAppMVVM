package com.example.theweatherapp.model

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class WeatherRepo(
    @SerializedName("consolidated_weather")
    var consolidated_weather:ArrayList<Weather>,

    @SerializedName("time")
    var time:Date,

    @SerializedName("sun_rise")
    var sun_rise:Date,

    @SerializedName("sun_set")
    var sun_set:Date,

    @SerializedName("timezone_name")
    var timezone_name:String,

    @SerializedName("parent")
    var parent:Parent,

    @SerializedName("sources")
    var sources:ArrayList<Sources>,

    @SerializedName("title")
    var title:String,

    @SerializedName("location_type")
    var location_type:String,

    @SerializedName("woeid")
    var woeid:Int,

    @SerializedName("latt_long")
    var latt_long:String,

    @SerializedName("timezone")
    var timezone:String


) {
}