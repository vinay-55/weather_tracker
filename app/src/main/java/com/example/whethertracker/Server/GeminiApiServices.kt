package com.example.whethertracker.Server

import com.example.whethertracker.Model.GeminiRequest
import com.example.whethertracker.Model.GeminiResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface GeminiApiService {

    @POST("models/gemini-flash-latest:generateContent")
    suspend fun generateContent(
        @Query("key") apiKey: String,
        @Body request: GeminiRequest
    ): GeminiResponse
}/*curl "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent" \
-H 'Content-Type: application/json' \
-H 'X-goog-api-key: AIzaSyC047T4x0DRySlNalIRlrouMzAVg4tTAmo' \
-X POST \
-d '{
"contents": [
{
    "parts": [
    {
        "text": "Explain how AI works in a few words"
    }
    ]
}
]
}'*/