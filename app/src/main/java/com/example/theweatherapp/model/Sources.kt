package com.example.theweatherapp.model

import com.google.gson.annotations.SerializedName

data class Sources(
    @SerializedName("title")
    var title:String,

    @SerializedName("slug")
    var slug:String,

    @SerializedName("url")
    var url:String,

    @SerializedName("crawl_rate")
    var crawl_rate:Int
) {
}