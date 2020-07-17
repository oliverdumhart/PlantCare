package com.oliverdumhart.plantcare

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oliverdumhart.plantcare.database.PlantDatabase

class MainViewModelFactory(val database: PlantDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(database) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}