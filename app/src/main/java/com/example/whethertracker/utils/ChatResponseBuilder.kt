package com.example.whethertracker.utils

import com.example.whethertracker.data.local.Crop

object ChatResponseBuilder {

    fun buildResponse(intent: String, input: String, crop: Crop): String {

        return when (intent) {

            "water" -> """
💧 Water Info:
Requirement: ${crop.waterRequirement}
Frequency: ${crop.wateringFrequency}
Method: ${crop.irrigationMethod}
Stage Advice: ${crop.wateringAtStage}
""".trimIndent()

            "soil" -> """
🌿 Soil Info:
Type: ${crop.soilTypes}
pH: ${crop.soilPHRange}
Drainage: ${crop.soilDrainage}
Fertility: ${crop.soilFertility}
""".trimIndent()

            "disease" -> """
🦠 Diseases:
${crop.commonDiseases}
Pests: ${crop.commonPests}
Prevention: ${crop.diseasePrevention}
""".trimIndent()

            "day" -> {
                val day = input.filter { it.isDigit() }.toIntOrNull()
                if (day != null) ChatHelper.getPhaseInfo(crop, day)
                else "Enter valid day"
            }

            else -> "Ask about water, soil, disease or day"
        }
    }
}