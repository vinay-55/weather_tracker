package com.example.whethertracker.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whethertracker.Repository.CropRepository
import com.example.whethertracker.data.local.Crop
import kotlinx.coroutines.launch

class CropViewModel(private val repository: CropRepository) : ViewModel() {

    // Insert crop
    fun insertIfNotExist(crop: Crop) {
        viewModelScope.launch {
            repository.insertIfNotExist(crop)
        }
    }

    fun insertCrop(crop: Crop) {
        viewModelScope.launch {
            repository.insertCrop(crop)
        }
    }

    suspend fun getCropByName(name: String): Crop? {
        return repository.getCropByName(name)
    }

    // Get all crops (future use)
    val allCrops: LiveData<List<Crop>> =repository.allCrops

    }