package com.example.whethertracker.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.whethertracker.Repository.CropRepository

class CropViewModelFactory(
    private val repository: CropRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CropViewModel::class.java)) {
            return CropViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}