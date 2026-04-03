package com.example.whethertracker.utils

import com.example.whethertracker.data.local.Crop

object ChatHelper {

    fun detectIntent(input: String): String {
        val text = input.lowercase()
        val hasNumber = input.any { it.isDigit() }

        return when {
            hasNumber -> "day"
            text.contains("water") -> "water"
            text.contains("soil") -> "soil"
            text.contains("disease") || text.contains("pest") -> "disease"
            text.contains("temperature") || text.contains("climate") -> "climate"
            text.contains("sunlight") -> "sunlight"
            else -> "general"
        }
    }

    fun getPhaseInfo(crop: Crop, day: Int): String {
        return when (day) {

            in 1..5 -> crop.season1Phase1
            in 6..15 -> crop.season1Phase2
            in 16..25 -> crop.season1Phase3
            in 26..30 -> crop.season1Phase4

            in 31..40 -> crop.season2Phase1
            in 41..50 -> crop.season2Phase2
            in 51..55 -> crop.season2Phase3
            in 56..60 -> crop.season2Phase4

            in 61..70 -> crop.season3Phase1
            in 71..80 -> crop.season3Phase2
            in 81..85 -> crop.season3Phase3
            in 86..90 -> crop.season3Phase4

            else -> "Invalid day. Enter between 1–90"
        }
    }
}