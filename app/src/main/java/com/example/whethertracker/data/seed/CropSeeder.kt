package com.example.whethertracker.data.seed

import com.example.whethertracker.ViewModel.CropViewModel
import com.example.whethertracker.data.local.Crop

object CropSeeder {

    suspend fun seed(cropViewModel: CropViewModel) {

        cropViewModel.insertIfNotExist(
            Crop(
                name = "tomato",
                cropType = "Vegetable",
                growthDays = 90,

                soilTypes = "Loamy, sandy loam",
                soilPHRange = "6.0-7.5",
                soilMoistureLevel = "Moderate",
                soilDrainage = "Well-drained",
                soilTexture = "Loamy",
                soilFertility = "High",
                soilDiseases = "Root rot, damping off",
                soilBornePests = "Nematodes, grubs",
                waterRetention = "Medium",
                nitrogenNeed = "High",
                salinityTolerance = "Low",
                potassiumTolerance = "High",
                calciumTolerance = "Medium",
                magnesiumTolerance = "Medium",

                waterRequirement = "Moderate",
                wateringFrequency = "Every 2-3 days",
                irrigationMethod = "Drip",
                irrigationTime = "Morning",
                overwateringRisk = "Yes",
                droughtTolerance = "Low",
                waterLoggingRisk = "High",
                wateringAtStage = "High during flowering and fruiting",

                commonDiseases = "Blight, powdery mildew",
                leafDiseases = "Leaf curl, yellowing",
                fruitDiseases = "Blossom end rot, fruit rot",
                stemDiseases = "Stem rot",
                commonPests = "Aphids, whiteflies, caterpillars",
                diseaseResistance = "Medium",
                diseasePrevention = "Proper spacing, avoid excess watering, apply fungicide",

                sunlight = "Full sun",

                minTemp = 18,
                maxTemp = 30,

                plantSpacingCm = "45-60 cm",

                // 🌱 SEASON 1 (Day 1–30)
                season1Phase1 = "Land preparation and soil testing",
                season1Phase1Days = "1-5",

                season1Phase2 = "Sowing seeds and initial watering",
                season1Phase2Days = "6-15",

                season1Phase3 = "Seed germination and early growth monitoring",
                season1Phase3Days = "16-25",

                season1Phase4 = "Vegetative growth and basic pest control",
                season1Phase4Days = "26-30",

                // 🌿 SEASON 2 (Day 31–60)
                season2Phase1 = "Transplanting seedlings and root establishment",
                season2Phase1Days = "31-40",

                season2Phase2 = "Leaf growth and nutrient application",
                season2Phase2Days = "41-50",

                season2Phase3 = "Flowering stage with increased watering",
                season2Phase3Days = "51-55",

                season2Phase4 = "Fruit setting and pest monitoring",
                season2Phase4Days = "56-60",

                // 🍅 SEASON 3 (Day 61–90)
                season3Phase1 = "Fruit development and size increase",
                season3Phase1Days = "61-70",

                season3Phase2 = "Ripening begins and watering control",
                season3Phase2Days = "71-80",

                season3Phase3 = "Harvesting ripe tomatoes",
                season3Phase3Days = "81-85",

                season3Phase4 = "Final harvesting and plant cleanup",
                season3Phase4Days = "86-90"
            )
        )
        cropViewModel.insertIfNotExist(
        Crop(
            name = "rice",
            cropType = "Grain",
            growthDays = 120,

            soilTypes = "Clay, loamy",
            soilPHRange = "5.5-7.0",
            soilMoistureLevel = "High",
            soilDrainage = "Poor to moderate (water retention needed)",
            soilTexture = "Clayey",
            soilFertility = "High",
            soilDiseases = "Root rot, sheath blight",
            soilBornePests = "Nematodes",
            waterRetention = "High",
            nitrogenNeed = "High",
            salinityTolerance = "Low",
            potassiumTolerance = "Medium",
            calciumTolerance = "Medium",
            magnesiumTolerance = "Medium",

            waterRequirement = "High",
            wateringFrequency = "Continuous flooding",
            irrigationMethod = "Flood",
            irrigationTime = "Throughout day",
            overwateringRisk = "No",
            droughtTolerance = "Low",
            waterLoggingRisk = "Low (tolerates water)",
            wateringAtStage = "High throughout growth",

            commonDiseases = "Blast, bacterial leaf blight",
            leafDiseases = "Leaf blast, yellowing",
            fruitDiseases = "Grain discoloration",
            stemDiseases = "Stem rot",
            commonPests = "Brown planthopper, stem borer",
            diseaseResistance = "Medium",
            diseasePrevention = "Proper spacing, resistant varieties, pest control",

            sunlight = "Full sun",

            minTemp = 20,
            maxTemp = 35,

            plantSpacingCm = "20-25 cm",

            // 🌱 SEASON 1 (Day 1–40)
            season1Phase1 = "Land preparation and puddling",
            season1Phase1Days = "1-10",

            season1Phase2 = "Nursery seed sowing",
            season1Phase2Days = "11-20",

            season1Phase3 = "Seedling growth in nursery",
            season1Phase3Days = "21-30",

            season1Phase4 = "Transplanting seedlings to field",
            season1Phase4Days = "31-40",

            // 🌿 SEASON 2 (Day 41–80)
            season2Phase1 = "Tillering stage (multiple shoots growth)",
            season2Phase1Days = "41-55",

            season2Phase2 = "Vegetative growth and nutrient application",
            season2Phase2Days = "56-65",

            season2Phase3 = "Panicle initiation (flower formation)",
            season2Phase3Days = "66-75",

            season2Phase4 = "Flowering stage",
            season2Phase4Days = "76-80",

            // 🌾 SEASON 3 (Day 81–120)
            season3Phase1 = "Grain filling stage",
            season3Phase1Days = "81-95",

            season3Phase2 = "Grain maturation",
            season3Phase2Days = "96-105",

            season3Phase3 = "Harvest preparation",
            season3Phase3Days = "106-115",

            season3Phase4 = "Harvesting and drying",
            season3Phase4Days = "116-120"
        )
        )
        cropViewModel.insertIfNotExist(
        Crop(
            name = "maize",
            cropType = "Grain",
            growthDays = 100,

            soilTypes = "Loamy, sandy loam",
            soilPHRange = "5.5-7.5",
            soilMoistureLevel = "Moderate",
            soilDrainage = "Well-drained",
            soilTexture = "Loamy",
            soilFertility = "Medium to high",
            soilDiseases = "Root rot, seed rot",
            soilBornePests = "Nematodes, wireworms",
            waterRetention = "Medium",
            nitrogenNeed = "High",
            salinityTolerance = "Low",
            potassiumTolerance = "Medium",
            calciumTolerance = "Medium",
            magnesiumTolerance = "Medium",

            waterRequirement = "Moderate",
            wateringFrequency = "Every 3-4 days",
            irrigationMethod = "Furrow or drip",
            irrigationTime = "Morning",
            overwateringRisk = "Yes",
            droughtTolerance = "Medium",
            waterLoggingRisk = "High",
            wateringAtStage = "High during tasseling and grain filling",

            commonDiseases = "Leaf blight, rust",
            leafDiseases = "Leaf spot, yellow streak",
            fruitDiseases = "Cob rot",
            stemDiseases = "Stalk rot",
            commonPests = "Armyworm, corn borer",
            diseaseResistance = "Medium",
            diseasePrevention = "Crop rotation, pest monitoring, proper spacing",

            sunlight = "Full sun",

            minTemp = 18,
            maxTemp = 32,

            plantSpacingCm = "25-30 cm",

            // 🌱 SEASON 1 (Day 1–30)
            season1Phase1 = "Land preparation and soil enrichment",
            season1Phase1Days = "1-7",

            season1Phase2 = "Sowing seeds",
            season1Phase2Days = "8-15",

            season1Phase3 = "Germination and early growth",
            season1Phase3Days = "16-25",

            season1Phase4 = "Vegetative growth begins",
            season1Phase4Days = "26-30",

            // 🌿 SEASON 2 (Day 31–70)
            season2Phase1 = "Rapid vegetative growth",
            season2Phase1Days = "31-45",

            season2Phase2 = "Leaf development and nutrient application",
            season2Phase2Days = "46-55",

            season2Phase3 = "Tasseling stage (flowering begins)",
            season2Phase3Days = "56-65",

            season2Phase4 = "Silking stage (pollination)",
            season2Phase4Days = "66-70",

            // 🌽 SEASON 3 (Day 71–100)
            season3Phase1 = "Grain formation",
            season3Phase1Days = "71-85",

            season3Phase2 = "Grain filling",
            season3Phase2Days = "86-92",

            season3Phase3 = "Drying stage",
            season3Phase3Days = "93-97",

            season3Phase4 = "Harvesting",
            season3Phase4Days = "98-100"
        )
        )
        cropViewModel.insertIfNotExist(
        Crop(
            name = "wheat",
            cropType = "Grain",
            growthDays = 110,

            soilTypes = "Loamy, clay loam",
            soilPHRange = "6.0-7.5",
            soilMoistureLevel = "Moderate",
            soilDrainage = "Well-drained",
            soilTexture = "Loamy",
            soilFertility = "Medium to high",
            soilDiseases = "Root rot, smut",
            soilBornePests = "Termites",
            waterRetention = "Medium",
            nitrogenNeed = "Medium",
            salinityTolerance = "Low",
            potassiumTolerance = "Medium",
            calciumTolerance = "Medium",
            magnesiumTolerance = "Medium",

            waterRequirement = "Moderate",
            wateringFrequency = "Every 5-7 days",
            irrigationMethod = "Flood or sprinkler",
            irrigationTime = "Morning or evening",
            overwateringRisk = "Yes",
            droughtTolerance = "Medium",
            waterLoggingRisk = "High",
            wateringAtStage = "Critical at tillering and grain filling",

            commonDiseases = "Rust, powdery mildew",
            leafDiseases = "Leaf rust, yellowing",
            fruitDiseases = "Grain shriveling",
            stemDiseases = "Stem rust",
            commonPests = "Aphids, termites",
            diseaseResistance = "Medium",
            diseasePrevention = "Use resistant varieties, proper irrigation",

            sunlight = "Full sun",

            minTemp = 10,
            maxTemp = 25,

            plantSpacingCm = "20-25 cm",

            // 🌱 SEASON 1 (Day 1–35)
            season1Phase1 = "Land preparation and ploughing",
            season1Phase1Days = "1-10",

            season1Phase2 = "Sowing seeds",
            season1Phase2Days = "11-20",

            season1Phase3 = "Germination and early growth",
            season1Phase3Days = "21-30",

            season1Phase4 = "Initial vegetative growth",
            season1Phase4Days = "31-35",

            // 🌿 SEASON 2 (Day 36–75)
            season2Phase1 = "Tillering stage (shoot development)",
            season2Phase1Days = "36-50",

            season2Phase2 = "Stem elongation",
            season2Phase2Days = "51-60",

            season2Phase3 = "Booting stage (flower preparation)",
            season2Phase3Days = "61-70",

            season2Phase4 = "Flowering",
            season2Phase4Days = "71-75",

            // 🌾 SEASON 3 (Day 76–110)
            season3Phase1 = "Grain formation",
            season3Phase1Days = "76-90",

            season3Phase2 = "Grain filling",
            season3Phase2Days = "91-100",

            season3Phase3 = "Maturation",
            season3Phase3Days = "101-105",

            season3Phase4 = "Harvesting",
            season3Phase4Days = "106-110"
        )
        )
        cropViewModel.insertIfNotExist(
        Crop(
            name = "potato",
            cropType = "Vegetable",
            growthDays = 90,

            soilTypes = "Sandy loam, loamy",
            soilPHRange = "5.0-6.5",
            soilMoistureLevel = "Moderate",
            soilDrainage = "Well-drained",
            soilTexture = "Sandy loam",
            soilFertility = "High",
            soilDiseases = "Black scurf, common scab",
            soilBornePests = "Nematodes, wireworms",
            waterRetention = "Medium",
            nitrogenNeed = "Medium",
            salinityTolerance = "Low",
            potassiumTolerance = "High",
            calciumTolerance = "Medium",
            magnesiumTolerance = "Medium",

            waterRequirement = "Moderate",
            wateringFrequency = "Every 3-5 days",
            irrigationMethod = "Furrow or drip",
            irrigationTime = "Morning",
            overwateringRisk = "Yes",
            droughtTolerance = "Low",
            waterLoggingRisk = "High",
            wateringAtStage = "Critical during tuber formation",

            commonDiseases = "Late blight, early blight",
            leafDiseases = "Leaf spot, yellowing",
            fruitDiseases = "Tuber rot",
            stemDiseases = "Stem rot",
            commonPests = "Aphids, potato beetle",
            diseaseResistance = "Medium",
            diseasePrevention = "Crop rotation, proper drainage, fungicide spray",

            sunlight = "Full sun",

            minTemp = 15,
            maxTemp = 25,

            plantSpacingCm = "30-40 cm",

            // 🌱 SEASON 1 (Day 1–30)
            season1Phase1 = "Land preparation and soil loosening",
            season1Phase1Days = "1-7",

            season1Phase2 = "Planting seed tubers",
            season1Phase2Days = "8-15",

            season1Phase3 = "Sprouting and early growth",
            season1Phase3Days = "16-25",

            season1Phase4 = "Vegetative growth begins",
            season1Phase4Days = "26-30",

            // 🌿 SEASON 2 (Day 31–60)
            season2Phase1 = "Leaf development and nutrient application",
            season2Phase1Days = "31-40",

            season2Phase2 = "Tuber initiation",
            season2Phase2Days = "41-50",

            season2Phase3 = "Tuber development",
            season2Phase3Days = "51-55",

            season2Phase4 = "Bulking stage (tuber growth)",
            season2Phase4Days = "56-60",

            // 🥔 SEASON 3 (Day 61–90)
            season3Phase1 = "Tuber maturation",
            season3Phase1Days = "61-75",

            season3Phase2 = "Skin hardening",
            season3Phase2Days = "76-80",

            season3Phase3 = "Harvest preparation",
            season3Phase3Days = "81-85",

            season3Phase4 = "Harvesting",
            season3Phase4Days = "86-90"
        )
        )
    }
}