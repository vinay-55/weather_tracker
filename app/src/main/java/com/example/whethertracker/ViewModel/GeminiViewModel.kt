package com.example.whethertracker.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whethertracker.Model.ChatMessage
import com.example.whethertracker.Model.Project
import com.example.whethertracker.Repository.GeminiRepository
import com.example.whethertracker.Server.GeminiApiClient
import com.example.whethertracker.Server.GeminiApiService
import kotlinx.coroutines.launch

class GeminiViewModel(private val repository: GeminiRepository) : ViewModel() {
    var currentProject: Project?=null

    val chatList=mutableListOf<ChatMessage>()
    fun setProject(project: Project){
        currentProject=project
    }
    fun addUserMessage(message: String){
        chatList.add(ChatMessage(message,true))
    }
    fun addBotMessage(message: String){
        chatList.add(ChatMessage(message, false))
    }
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
                addBotMessage(result)
                onResult(result)
            } catch (e: Exception) {
                onError(e.toString())
            }
        }
    }
}