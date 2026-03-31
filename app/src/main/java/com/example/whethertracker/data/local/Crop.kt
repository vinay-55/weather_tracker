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
    val waterNeed: String,
    val sunlight: String,

    val minTemp: Int,
    val maxTemp: Int,

    val plantSpacingCm: String
)