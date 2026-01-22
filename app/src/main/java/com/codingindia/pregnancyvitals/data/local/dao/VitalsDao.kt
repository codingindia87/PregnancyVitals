package com.codingindia.pregnancyvitals.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.codingindia.pregnancyvitals.data.local.entity.PregnancyVitals
import kotlinx.coroutines.flow.Flow

@Dao
interface VitalsDao {
    @Insert
    suspend fun insertVitals(vitals: PregnancyVitals)

    @Query("SELECT * FROM pregnancy_vitals ORDER BY date DESC")
    fun getAllVitals(): Flow<List<PregnancyVitals>>
}