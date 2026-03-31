package com.example.whethertracker.data.local
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CropDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrop(crop: Crop)
    @Query("SELECT * FROM crops WHERE name = :name LIMIT 1")
    suspend fun getCropByName(name: String): Crop?
    @Query("SELECT * FROM crops")
    fun getAllCrops(): LiveData<List<Crop>>
}