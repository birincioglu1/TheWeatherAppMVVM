package com.example.theweatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.theweatherapp.CityFragmentDirections
import com.example.theweatherapp.R
import com.example.theweatherapp.databinding.SingleLineCityBinding
import com.example.theweatherapp.model.City
import kotlinx.android.synthetic.main.single_line_city.view.*

class CityAdapter(var cityList:ArrayList<City>):RecyclerView.Adapter<CityAdapter.CityViewHolder>(),CityClickListener {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        var inflater=LayoutInflater.from(parent.context)
        val view=DataBindingUtil.inflate<SingleLineCityBinding>(inflater,R.layout.single_line_city,parent,false)
        return CityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.view.city=cityList[position]
        holder.view.listener=this


    }

    class CityViewHolder(var view: SingleLineCityBinding):RecyclerView.ViewHolder(view.root) {

    }

    fun updateCityList(newCityList:ArrayList<City>)
    {
        cityList.clear()
        cityList.addAll(newCityList)
        notifyDataSetChanged()
    }

    override fun onCityClicked(v: View) {
        val cityId=v.cityId.text.toString().toInt()
        val action=CityFragmentDirections.actionCityFragmentToDetailFragment(cityId)

        Navigation.findNavController(v).navigate(action)
    }
}
