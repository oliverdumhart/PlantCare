package com.oliverdumhart.plantcare.plantoverview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oliverdumhart.plantcare.database.PlantDatabase
import com.oliverdumhart.plantcare.models.Plant

class PlantViewModelFactory(private val plant: Plant) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlantViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlantViewModel(plant) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}