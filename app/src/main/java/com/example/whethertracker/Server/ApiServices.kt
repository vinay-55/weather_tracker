package com.example.whethertracker.Server

import com.example.whethertracker.Model.CityResponseApi
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
    @GET("geo/1.0/direct")
    fun getCitiesList(
        @Query("q") q:String,
        @Query("limit") limit:Int,
        @Query("appid") Apikey:String
    ): Call<CityResponseApi>

}