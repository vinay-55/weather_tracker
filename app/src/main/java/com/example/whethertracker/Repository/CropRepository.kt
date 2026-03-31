package com.example.whethertracker.Repository

import androidx.lifecycle.LiveData
import com.example.whethertracker.data.local.CropDao
import com.example.whethertracker.data.local.Crop

class CropRepository(private val cropDao: CropDao) {
    suspend fun insertIfNotExist(crop: Crop) {
        val existing = cropDao.getCropByName(crop.name)
        if (existing == null) {
            cropDao.insertCrop(crop)
        }
    }

    suspend fun insertCrop(crop: Crop) {
        cropDao.insertCrop(crop)
    }

    suspend fun getCropByName(name: String): Crop? {
        return cropDao.getCropByName(name)
    }

        val allCrops: LiveData<List<Crop>> = cropDao.getAllCrops()
    }