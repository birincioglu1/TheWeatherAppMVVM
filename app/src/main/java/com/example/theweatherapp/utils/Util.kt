package com.example.theweatherapp.utils

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.theweatherapp.R
import java.text.SimpleDateFormat

import java.util.*

fun ImageView.downloadFromUrl( id:String?,progressDrawable: CircularProgressDrawable)
{
    var url:String="https://www.metaweather.com/static/img/weather/png/64/"
    url= "$url$id.png"
    val options=RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.ic_error)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}
fun placeholderProgressBar(context: Context):CircularProgressDrawable
{
    return CircularProgressDrawable(context).apply {
        strokeWidth=8f
        centerRadius=40f
        start()
    }
}

@BindingAdapter("android:downloadUrl")
fun downloadImage(view:ImageView,id: String?)
{

        view.downloadFromUrl(id, placeholderProgressBar(view.context))

}

@BindingAdapter("android:parseDay")
fun getDay(view:TextView,date: Date?)
{
        val simpleDaFormatter=SimpleDateFormat("EEEE",Locale.ENGLISH).format(date!!)
        view.text=simpleDaFormatter
}

@BindingAdapter("android:formatWind")
fun formatWind(view:TextView,value:Float?) {
    val newFormat = "%.1f km/h".format(value)
    view.text = newFormat
}
@BindingAdapter("android:formatPercent")
fun formatPercent(view:TextView,value:Float?)
{
    var mValue=value!!.toInt()
    val newFormat="% $mValue"
    view.text=newFormat
 }
@BindingAdapter("android:formatPressure")
 fun formatPressure(view:TextView,value:Float?)
 {
     val newFormat="%.1f hPa".format(value)
     view.text=newFormat
 }
@BindingAdapter("android:formatFloat")
fun formatFloat(view:TextView,value:Float?)
{
    val newFormat="%.1f".format(value)
    view.text=newFormat
}
@BindingAdapter("android:formatDegree")
fun formatDegre(view:TextView,value:Float?)
{
    val mValue=value!!.toInt()
    val newFormat="$mValue \u2103"
    view.text=newFormat
}
