package com.oliverdumhart.plantcare.utils

import com.oliverdumhart.plantcare.database.DatabasePlant
import com.oliverdumhart.plantcare.models.Plant

fun DatabasePlant.asDomainModel(): Plant = Plant(
    id = this.id,
    name = this.name,
    waterNeeds = this.waterNeeds,
    fertilizerNeeds = this.fertilizerNeeds,
    sunlight = this.sunlight,
    repotting = this.repotting,
    generalInformation = this.generalInformation
)

fun Plant.asDatabaseModel(): DatabasePlant = DatabasePlant(
    id = this.id,
    name = this.name,
    waterNeeds = this.waterNeeds,
    fertilizerNeeds = this.fertilizerNeeds,
    sunlight = this.sunlight,
    repotting = this.repotting,
    generalInformation = this.generalInformation
)