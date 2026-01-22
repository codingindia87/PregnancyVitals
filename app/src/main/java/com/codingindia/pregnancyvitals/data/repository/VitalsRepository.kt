package com.codingindia.pregnancyvitals.data.repository

import com.codingindia.pregnancyvitals.data.local.dao.VitalsDao
import com.codingindia.pregnancyvitals.data.local.entity.PregnancyVitals
import jakarta.inject.Inject

class VitalsRepository @Inject constructor(private val vitalsDao: VitalsDao) {
    fun getAllVitals() = vitalsDao.getAllVitals()
    suspend fun insertVitals(vitals: PregnancyVitals) = vitalsDao.insertVitals(vitals)
}