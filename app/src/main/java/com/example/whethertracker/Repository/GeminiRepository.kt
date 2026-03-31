package com.example.whethertracker.Repository

import android.util.Log
import com.example.whethertracker.Model.Content
import com.example.whethertracker.Model.GeminiRequest
import com.example.whethertracker.Model.Part
import com.example.whethertracker.Server.GeminiApiService

class GeminiRepository(private val api: GeminiApiService) {

    suspend fun getGeminiResponse(apiKey: String, userInput: String): String {
        Log.d("API CALLED AGAIN","$userInput")

        val request = GeminiRequest(
            contents = listOf(
                Content(
                    parts = listOf(
                        Part(userInput)
                    )
                )
            )
        )

        val response = api.generateContent(apiKey, request)

        val text = response.candidates
            .firstOrNull()
            ?.content
            ?.parts
            ?.firstOrNull()
            ?.text

        return text ?: "No response from AI"
    }
}