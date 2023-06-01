package com.dicoding.athleteapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Athlete(
    var name: String,
    var description: String,
    var foto: String,
    var birth: String,
    var sports: String,
    var country: String
) : Parcelable
