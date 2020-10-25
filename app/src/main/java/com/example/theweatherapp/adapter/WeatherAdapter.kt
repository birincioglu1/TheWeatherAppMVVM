package com.example.theweatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.theweatherapp.R
import com.example.theweatherapp.databinding.SingleLineWeatherBinding
import com.example.theweatherapp.model.Weather


class WeatherAdapter(var weatherList:ArrayList<Weather>): RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {


    class WeatherViewHolder(var view: SingleLineWeatherBinding):RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        var inflater= LayoutInflater.from(parent.context)
        val view=DataBindingUtil.inflate<SingleLineWeatherBinding>(inflater, R.layout.single_line_weather,parent,false)
        return WeatherViewHolder(view)
    }

    override fun getItemCount(): Int {
       return weatherList.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.view.weather=weatherList[position]
    }
    fun updateWeatherList(newCityList:ArrayList<Weather>)
    {
        weatherList.clear()
        weatherList.addAll(newCityList)
        notifyDataSetChanged()
    }
}