package com.oliverdumhart.plantcare.plantinformation

import android.app.Application
import android.graphics.Bitmap
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
    private val _picture = MutableLiveData<Bitmap?>()
    val picture: LiveData<Bitmap?>
        get() = _picture

    init {
        plant = if (plantId == 0L) {
            MutableLiveData(Plant())
        } else {
            plantRepository.getPlantById(plantId)
        }
        _picture.value = plant.value?.picture
    }

        private val _finishedEvent = MutableLiveData<Boolean>(false)
        val finishedEvent: LiveData<Boolean>
        get() = _finishedEvent

        fun finishedEventFinished() {
            _finishedEvent.value = false
        }

        fun saveChanges() {
            viewModelScope.launch {
                val p = plant.value!!
                p.picture = picture.value
                plantRepository.insertPlant(p)
            }
            _finishedEvent.value = true
        }

        private val _pictureEvent = MutableLiveData<Boolean>(false)
        val pictureEvent: LiveData<Boolean>
        get() = _pictureEvent

        fun onPictureClicked() {
            _pictureEvent.value = true
        }

        fun pictureEventFinished() {
            _pictureEvent.value = false
        }

        override fun onCleared() {
            super.onCleared()
            viewModelJob.cancel()
        }

        fun updatePicture(bitmap: Bitmap) {
            this._picture.value = bitmap
        }
    }