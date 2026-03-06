package com.example.whethertracker.ViewModel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.whethertracker.Repository.WeatherRepository
import com.example.whethertracker.Server.ApiClient
import com.example.whethertracker.Server.ApiServices

class WeatherViewModel: ViewModel() {
    private val repository=WeatherRepository(ApiClient().getClient().create(ApiServices::class.java))
    fun loadCurrentWeather(lat:Double,lng:Double,unit:String)=
        repository.getWhether(lat,lng,unit)
    fun loadForecastWeather(lat:Double,lng:Double,unit:String)=
        repository.getForecast(lat,lng,unit)


}