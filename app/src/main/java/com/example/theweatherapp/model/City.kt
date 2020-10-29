package com.example.theweatherapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class City (
    @ColumnInfo(name = "distance")
    @SerializedName("distance")
    val distance:Int?,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title:String?,

    @ColumnInfo(name = "location_type")
    @SerializedName("location_type")
    val location:String?,

    @ColumnInfo(name = "woeid")
    @SerializedName("woeid")
    val woeid:Int?,

    @ColumnInfo(name = "latt_long")
    @SerializedName("latt_long")
    val latt:String?)
{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}