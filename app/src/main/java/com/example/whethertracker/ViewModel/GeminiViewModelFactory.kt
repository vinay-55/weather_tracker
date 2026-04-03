package com.example.whethertracker.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.whethertracker.Repository.GeminiRepository

class GeminiViewModelFactory(
    private val repository: GeminiRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GeminiViewModel(repository) as T
    }
}