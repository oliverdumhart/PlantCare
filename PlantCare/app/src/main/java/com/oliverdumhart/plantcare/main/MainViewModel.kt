package com.oliverdumhart.plantcare.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oliverdumhart.plantcare.database.PlantDatabase
import com.oliverdumhart.plantcare.models.Plant
import com.oliverdumhart.plantcare.repository.PlantRepository

class MainViewModel(database: PlantDatabase) : ViewModel() {

    private val plantRepository = PlantRepository(database)

    val plants = plantRepository.plants

    private val _addPlantEvent = MutableLiveData<Boolean?>()
    val addPlantEvent : LiveData<Boolean?>
        get() = _addPlantEvent

    fun onAddPlantClicked() {
        _addPlantEvent.value = true
    }

    fun addPlantEventFinished(){
        _addPlantEvent.value = null
    }

    private val _showPlantEvent = MutableLiveData<Plant?>()
    val showPlantEvent : LiveData<Plant?>
        get() = _showPlantEvent

    fun onPlantClicked(plant: Plant) {
        _showPlantEvent.value = plant
    }

    fun showPlantEventFinished(){
        _showPlantEvent.value = null
    }
}