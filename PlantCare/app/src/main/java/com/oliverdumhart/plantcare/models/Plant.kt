package com.oliverdumhart.plantcare.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Plant (
    val id: Long = 0L,
    var name: String = "",
    var waterNeeds: String = "",
    var fertilizerNeeds: String = "",
    var sunlight: String = "",
    var repotting: String = "",
    var generalInformation: String = ""
): Parcelable