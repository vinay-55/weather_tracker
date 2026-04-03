package com.example.whethertracker.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "crops",
    indices =[Index(value = ["name"], unique = true)]
)
data class Crop(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val cropType: String,
    val growthDays: Int,

    val soilTypes: String,
    val soilPHRange: String,
    val soilMoistureLevel: String,
    val soilDrainage: String,
    val soilTexture: String,
    val soilFertility: String,
    val soilDiseases: String,
    val soilBornePests: String,
    val waterRetention: String,
    val nitrogenNeed: String,
    val salinityTolerance: String,
    val potassiumTolerance: String,
    val calciumTolerance: String,
    val magnesiumTolerance: String,

    val waterRequirement: String,
    val wateringFrequency: String,
    val irrigationMethod: String,
    val irrigationTime: String,
    val overwateringRisk: String,
    val droughtTolerance: String,
    val waterLoggingRisk: String,
    val wateringAtStage: String,

    val commonDiseases: String,
    val leafDiseases: String,
    val fruitDiseases: String,
    val stemDiseases: String,
    val commonPests: String,
    val diseaseResistance: String,
    val diseasePrevention: String,

    val sunlight: String,

    val minTemp: Int,
    val maxTemp: Int,

    val plantSpacingCm: String,

    // Season 1
    val season1Phase1: String,
    val season1Phase1Days: String,

    val season1Phase2: String,
    val season1Phase2Days: String,

    val season1Phase3: String,
    val season1Phase3Days: String,

    val season1Phase4: String,
    val season1Phase4Days: String,

// Season 2
    val season2Phase1: String,
    val season2Phase1Days: String,

    val season2Phase2: String,
    val season2Phase2Days: String,

    val season2Phase3: String,
    val season2Phase3Days: String,

    val season2Phase4: String,
    val season2Phase4Days: String,

// Season 3
    val season3Phase1: String,
    val season3Phase1Days: String,

    val season3Phase2: String,
    val season3Phase2Days: String,

    val season3Phase3: String,
    val season3Phase3Days: String,

    val season3Phase4: String,
    val season3Phase4Days: String
)