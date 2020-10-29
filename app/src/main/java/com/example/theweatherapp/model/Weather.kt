package com.example.theweatherapp.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Weather(
    @SerializedName("id")
    var id:Float,

    @SerializedName("weather_state_name")
    var weather_state_name:String,

    @SerializedName("weather_state_abbr")
    var weather_state_abbr:String,

    @SerializedName("wind_direction_compass")
    var wind_direction_compass:String,

    @SerializedName("created")
    var created:Date,

    @SerializedName("applicable_date")
    var applicable_date:Date,

    @SerializedName("min_temp")
    var min_temp:Float,

    @SerializedName("max_temp")
    var max_temp:Float,

    @SerializedName("the_temp")
    var the_temp:Float,

    @SerializedName("wind_speed")
    var wind_speed:Float,

    @SerializedName("wind_direction")
    var wind_direction:Float,

    @SerializedName("air_pressure")
    var air_pressure:Float,

    @SerializedName("humidity")
    var humidity:Float,

    @SerializedName("visibility")
    var visibility:Float,

    @SerializedName("predictability")
    var predictability:Float

) {
}