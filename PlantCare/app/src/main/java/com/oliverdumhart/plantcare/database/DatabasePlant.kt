package com.oliverdumhart.plantcare.database

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class DatabasePlant(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    var name: String = "",
    var waterNeeds: String = "",
    var fertilizerNeeds: String = "",
    var sunlight: String = "",
    var repotting: String = "",
    var generalInformation: String = "",
    var picture: ByteArray? = null
)