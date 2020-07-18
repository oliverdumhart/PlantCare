package com.oliverdumhart.plantcare.plantinformation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oliverdumhart.plantcare.database.PlantDatabase

class PlantInformationViewModelFactory(val database: PlantDatabase, var plantId: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlantInformationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlantInformationViewModel(
                database,
                plantId
            ) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}