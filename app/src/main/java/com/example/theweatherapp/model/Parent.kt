package com.example.theweatherapp.model

import com.google.gson.annotations.SerializedName

data class Parent(
    @SerializedName("title")
    var title:String,

    @SerializedName("location_type")
    var location_type:String,

    @SerializedName("woeid")
    var woeid:String,

    @SerializedName("latt_long")
    var latt_long:String
) {
}