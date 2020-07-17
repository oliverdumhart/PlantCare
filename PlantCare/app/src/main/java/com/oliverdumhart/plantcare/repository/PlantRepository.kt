package com.oliverdumhart.plantcare.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.oliverdumhart.plantcare.database.PlantDatabase
import com.oliverdumhart.plantcare.models.Plant
import com.oliverdumhart.plantcare.utils.asDatabaseModel
import com.oliverdumhart.plantcare.utils.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlantRepository(private val database: PlantDatabase) {

    val plants : LiveData<List<Plant>> = Transformations.map(database.plantDao.getPlants()) {
        it.map { it.asDomainModel() }
    }

    suspend fun insertPlant(plant: Plant){
        withContext(Dispatchers.IO) {
            database.plantDao.insertPlant(plant.asDatabaseModel())
        }
    }

    fun getPlantById(plantId: Long): LiveData<Plant> = Transformations.map(database.plantDao.getPlantById(plantId)){
        it.asDomainModel()
    }

}