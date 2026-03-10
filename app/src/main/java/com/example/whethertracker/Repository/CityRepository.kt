package com.example.whethertracker.Repository

import com.example.whethertracker.Server.ApiServices

class CityRepository(val api: ApiServices) {
    fun  getCities(q:String,limit:Int)=api.getCitiesList(q,limit,"b15e1d14a619902578b9e8f42a94de96")

}