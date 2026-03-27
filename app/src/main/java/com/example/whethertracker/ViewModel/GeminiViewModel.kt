package com.example.whethertracker.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whethertracker.Repository.GeminiRepository
import com.example.whethertracker.Server.GeminiApiClient
import com.example.whethertracker.Server.GeminiApiService
import kotlinx.coroutines.launch

class GeminiViewModel(repository1: GeminiRepository) : ViewModel() {

    private val api = GeminiApiClient().getClient().create(GeminiApiService::class.java)
    private val repository = GeminiRepository(api)

    fun getGeminiResponse(
        userInput: String,
        onResult: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val result = repository.getGeminiResponse(
                    apiKey = "AIzaSyC047T4x0DRySlNalIRlrouMzAVg4tTAmo",
                    userInput = userInput
                )
                onResult(result)
            } catch (e: Exception) {
                onError(e.toString())
            }
        }
    }
}