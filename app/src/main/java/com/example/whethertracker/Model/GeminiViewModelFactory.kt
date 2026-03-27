package com.example.whethertracker.Model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.whethertracker.Repository.GeminiRepository
import com.example.whethertracker.ViewModel.GeminiViewModel

class GeminiViewModelFactory(
    private val repository: GeminiRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GeminiViewModel(repository) as T
    }
}