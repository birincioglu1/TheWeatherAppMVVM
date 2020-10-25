package com.example.theweatherapp.model

import com.google.gson.annotations.SerializedName

data class City (
    @SerializedName("distance")
    val distance:Int?,
    @SerializedName("title")
    val title:String?,
    @SerializedName("location_type")
    val location_type:String?,
    @SerializedName("woeid")
    val woeid:Int?,
    @SerializedName("latt_long")
    val latt_long:String?)
