package com.example.theweatherapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theweatherapp.adapter.CityAdapter
import com.example.theweatherapp.adapter.WeatherAdapter
import com.example.theweatherapp.databinding.FragmentDetailBinding
import com.example.theweatherapp.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_city.*
import kotlinx.android.synthetic.main.fragment_detail.*
import java.util.zip.ZipEntry


class DetailFragment : Fragment() {

    private var mCityId=0
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding:FragmentDetailBinding
    private val weatherAdapter= WeatherAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false)
        dataBinding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            mCityId=DetailFragmentArgs.fromBundle(it).cityId
        }


        recylerWeek.layoutManager= LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recylerWeek.adapter=weatherAdapter
        viewModel= ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.refrehData(mCityId)
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.weathers.observe(viewLifecycleOwner, Observer { weather->
            weather?.let {
                dataBinding.selectedCityDetail=it
                dataBinding.selectedCity=it.consolidated_weather[0]
                weatherAdapter.updateWeatherList(weather.consolidated_weather)
            }
        })
        viewModel.weatherErr.observe(viewLifecycleOwner, Observer { err->
            err?.let {
                if (it)
                {
                    tvWeatherErr.visibility=View.VISIBLE

                }
                else{
                    tvWeatherErr.visibility=View.GONE
                }
            }
        })
        viewModel.weatherLoading.observe(viewLifecycleOwner, Observer { loading->
            loading?.let {
                if (it)
                {
                    pbWeather.visibility=View.VISIBLE
                    dataBinding.rootLayout.visibility=View.INVISIBLE
                    tvWeatherErr.visibility=View.GONE
                }else{
                    pbWeather.visibility=View.GONE
                    dataBinding.rootLayout.visibility=View.VISIBLE
                }
            }
        })
    }
}