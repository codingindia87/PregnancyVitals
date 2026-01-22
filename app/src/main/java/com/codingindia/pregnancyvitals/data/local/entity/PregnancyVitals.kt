package com.codingindia.pregnancyvitals.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pregnancy_vitals")
data class PregnancyVitals(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: Long,
    val weight: Double,
    val heartRate: Int,
    val systolicBP: Int,
    val diastolicBP: Int,
    val babyKick: Int
)