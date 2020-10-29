package com.example.theweatherapp.viewmodel


import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.theweatherapp.model.City
import com.example.theweatherapp.service.CityDatabase
import com.example.theweatherapp.service.WeatherAPIService
import com.example.theweatherapp.utils.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class CityViewModel(application: Application):BaseViewModel(application) {

    private val customPreferences=CustomSharedPreferences(getApplication())
    var cities=MutableLiveData<List<City>>()

    val cityErr=MutableLiveData<Boolean>()
    val cityLoading=MutableLiveData<Boolean>()
    private val weatherAPIService=WeatherAPIService()
    private val disposable=CompositeDisposable()
    private val refreshTime=1*60*1000*1000*1000L
    fun refreshData(coordinat:String)
    {

        val updateTime=customPreferences.getTime()
        if (updateTime!=null && updateTime != 0L && System.nanoTime()-updateTime < refreshTime)
        {
            getDataFromSQLite()
        }else{
            getDataFromAPI(coordinat)
        }
    }
    fun refreshFromAPI(coordinat:String)
    {
        getDataFromAPI(coordinat)
    }


    private fun getDataFromSQLite() {
        cityLoading.value=true
        launch {
            val cities= CityDatabase(getApplication()).cityDao().getAllCities()
            showCities(cities)
            Toast.makeText(getApplication(),"Cities From SQLite",Toast.LENGTH_LONG).show()
        }
    }

    private fun getDataFromAPI(latlong:String) {
        cityLoading.value=true
        disposable.add(
            weatherAPIService.getCities(latlong)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<ArrayList<City>>(){
                    override fun onSuccess(t: ArrayList<City>) {

                        storeInSQLite(t)
                        Toast.makeText(getApplication(),"Cities From API",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        cityLoading.value=false
                        cityErr.value=true
                        e.printStackTrace()

                    }

                })
        )

        /*var mWeatherAPIService=CityAPIService.client?.create(WeatherAPIService::class.java)
        var apicall=mWeatherAPIService?.getCities(latlong)
        apicall?.enqueue(object :Callback<ArrayList<City>>{
            override fun onFailure(call: Call<ArrayList<City>>, t: Throwable) {
                cityLoading.value=false
                cityErr.value=true
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<ArrayList<City>>,
                response: Response<ArrayList<City>>
            ) {
                if (response.isSuccessful)
                {
                    storeInSQLite(response.body()!!)
                    Toast.makeText(getApplication(),"Cities From API",Toast.LENGTH_LONG).show()
                }


            }


        })*/
    }

    private fun showCities(cityList: List<City>)
    {

        cities.value=cityList
        cityErr.value=false
        cityLoading.value=false
    }
    private fun storeInSQLite(list: ArrayList<City>)
    {
        launch {
            val dao=CityDatabase(getApplication()).cityDao()
            dao.deleteAllCities()
           val listLong= dao.insertAll(*list.toTypedArray())  //list

            var i=0
            while (i<list.size)
            {
                list[i].uuid=listLong[i].toInt()
                i++
            }
            showCities(list)

        }

        customPreferences.saveTime(System.nanoTime())
    }

}