package com.oliverdumhart.plantcare

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oliverdumhart.plantcare.database.PlantDatabase
import com.oliverdumhart.plantcare.models.Plant
import com.oliverdumhart.plantcare.repository.PlantRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PlantInformationViewModel(private val database: PlantDatabase, private val plantId: Long) :
    ViewModel() {

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val plantRepository = PlantRepository(database)

    val plant: LiveData<Plant>

    init {
        plant = if (plantId == 0L) {
            MutableLiveData(Plant())
        } else {
            plantRepository.getPlantById(plantId)
        }
    }

    private val _finishedEvent = MutableLiveData<Boolean>()
    val finishedEvent: LiveData<Boolean>
        get() = _finishedEvent

    fun finishedEventFinished(){
        _finishedEvent.value = false
    }

    fun saveChanges() {
        viewModelScope.launch {
            plantRepository.insertPlant(plant.value!!)
        }
        _finishedEvent.value = true
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}