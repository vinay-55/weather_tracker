package com.example.whethertracker.Server

import com.example.whethertracker.Model.CurrentResponseApi
import com.example.whethertracker.Model.ForecastResponseApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("data/2.5/weather")
    fun getCurrentWhether(
        @Query("lat") lat: Double,
        @Query("lon") lon:Double,
        @Query("units") units:String,
        @Query("appid") Apikey:String
    ): Call<CurrentResponseApi>
    @GET("data/2.5/forecast")
    fun getForecastWhether(
        @Query("lat") lat: Double,
        @Query("lon") lon:Double,
        @Query("units") units:String,
        @Query("appid") Apikey:String
    ): Call<ForecastResponseApi>
}