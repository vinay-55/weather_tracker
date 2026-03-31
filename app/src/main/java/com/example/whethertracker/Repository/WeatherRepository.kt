package com.example.whethertracker.Repository

import com.example.whethertracker.Server.ApiServices


class WeatherRepository(val api: ApiServices) {
    fun getWhether(lat: Double, lon: Double, units: String) =
        api.getCurrentWhether(lat, lon, units, "b15e1d14a619902578b9e8f42a94de96")
    fun getForecast(lat: Double, lon: Double, units: String) =
        api.getForecastWhether(lat, lon, units, "b15e1d14a619902578b9e8f42a94de96")


}