package com.example.whethertracker.Repository

import Crop
import CropDao

class CropRepository(private val cropDao: CropDao) {

    suspend fun insertCrop(crop: Crop) {
        cropDao.insertCrop(crop)
    }

    suspend fun getAllCrops(): List<Crop> {
        return cropDao.getAllCrops()
    }
}