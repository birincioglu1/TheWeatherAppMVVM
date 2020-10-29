package com.example.theweatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theweatherapp.adapter.CityAdapter
import com.example.theweatherapp.viewmodel.CityViewModel
import kotlinx.android.synthetic.main.fragment_city.*
import kotlinx.android.synthetic.main.fragment_city.view.*


class CityFragment : Fragment() {

    private lateinit var viewModel: CityViewModel
    private val cityAdapter=CityAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var fragmentView=inflater.inflate(R.layout.fragment_city, container, false)

        fragmentView.btnBackToMain.setOnClickListener {
            requireActivity().onBackPressed()
        }
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProviders.of(this).get(CityViewModel::class.java)
        viewModel.refrehData()
        recyclerCity.layoutManager=LinearLayoutManager(context)
        recyclerCity.adapter=cityAdapter
        observeLiveData()
    }
    private fun observeLiveData()
    {
        viewModel.cities.observe(viewLifecycleOwner, Observer {cities->
        cities?.let {
            recyclerCity.visibility=View.VISIBLE
            cityAdapter.updateCityList(cities)
        }
        })
        viewModel.cityErr.observe(viewLifecycleOwner, Observer { err->
            err?.let {
            if (it)
            {
                tvCityErr.visibility=View.VISIBLE
            }else{
               tvCityErr.visibility=View.GONE
            }
            }
        })
        viewModel.cityLoading.observe(viewLifecycleOwner, Observer { loading->
            loading?.let {
                if (it)
                {
                    pbCityLoading.visibility=View.VISIBLE
                    recyclerCity.visibility=View.GONE
                    tvCityErr.visibility=View.GONE
                }else{
                    pbCityLoading.visibility=View.GONE

                }
            }
        })

    }


}