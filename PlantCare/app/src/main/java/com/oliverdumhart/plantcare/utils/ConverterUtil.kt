package com.oliverdumhart.plantcare.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.oliverdumhart.plantcare.database.DatabasePlant
import com.oliverdumhart.plantcare.models.Plant
import java.io.ByteArrayOutputStream

fun DatabasePlant.asDomainModel(): Plant = Plant(
    id = this.id,
    name = this.name,
    waterNeeds = this.waterNeeds,
    fertilizerNeeds = this.fertilizerNeeds,
    sunlight = this.sunlight,
    repotting = this.repotting,
    generalInformation = this.generalInformation,
    picture = this.picture?.let {
        BitmapFactory.decodeByteArray(it, 0, it.size)
    }
)

fun Plant.asDatabaseModel(): DatabasePlant {
    val plant = DatabasePlant(
        id = this.id,
        name = this.name,
        waterNeeds = this.waterNeeds,
        fertilizerNeeds = this.fertilizerNeeds,
        sunlight = this.sunlight,
        repotting = this.repotting,
        generalInformation = this.generalInformation
    )

    this.picture?.let {
        val stream = ByteArrayOutputStream()
        it.compress(Bitmap.CompressFormat.WEBP, 100, stream)
        val bytes = stream.toByteArray()
        it.recycle()
        plant.picture = bytes
    }

    return plant
}