package com.oliverdumhart.plantcare.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlantDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlant(plant: DatabasePlant)

    @Query("select * from databaseplant where id = :id")
    fun getPlantById(id: Long): LiveData<DatabasePlant>

    @Query("select * from databaseplant")
    fun getPlants(): LiveData<List<DatabasePlant>>
}