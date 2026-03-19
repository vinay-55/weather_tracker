package com.example.whethertracker.ViewModel

import androidx.lifecycle.ViewModel
import com.example.whethertracker.Repository.CityRepository
import com.example.whethertracker.Server.ApiClient
import com.example.whethertracker.Server.ApiServices

class CityViewModel(val repository: CityRepository): ViewModel() {
    constructor():this(CityRepository(ApiClient().getClient().create(ApiServices::class.java)))

    /*private val repository= CityRepository(ApiClient().getClient().create(ApiServices::class.java))*/
    fun loadCitiesList(q:String,limit:Int)=repository.getCities(q,limit)

}