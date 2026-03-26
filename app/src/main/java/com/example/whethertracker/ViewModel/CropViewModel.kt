package com.example.whethertracker.ViewModel

import Crop
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whethertracker.Repository.CropRepository
import kotlinx.coroutines.launch

class CropViewModel(private val repository: CropRepository) : ViewModel() {

    fun insertCrop(crop: Crop) {
        viewModelScope.launch {
            repository.insertCrop(crop)
        }
    }

    suspend fun getAllCrops(): List<Crop> {
        return repository.getAllCrops()
    }
}