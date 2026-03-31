package com.example.whethertracker.Server

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class GeminiApiClient {
    private lateinit var retrofit: Retrofit
    private val client= OkHttpClient.Builder()
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120,TimeUnit.SECONDS)
        .writeTimeout(120,TimeUnit.SECONDS)
        .build()
    fun getClient():Retrofit{
        retrofit=Retrofit.Builder()
            .baseUrl("https://generativelanguage.googleapis.com/v1beta/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}/*2)curl "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent" \
-H 'Content-Type: application/json' \
-H 'X-goog-api-key: AIzaSyCzgCquoXYvH-AB5yMCDiH1Zm6E04N6B1M' \
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