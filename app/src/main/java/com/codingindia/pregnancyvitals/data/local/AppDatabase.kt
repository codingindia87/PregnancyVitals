package com.codingindia.pregnancyvitals.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingindia.pregnancyvitals.data.local.dao.VitalsDao
import com.codingindia.pregnancyvitals.data.local.entity.PregnancyVitals

@Database(entities = [PregnancyVitals::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vitalsDao(): VitalsDao
}