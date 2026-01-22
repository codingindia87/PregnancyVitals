package com.codingindia.pregnancyvitals.di

import android.content.Context
import androidx.room.Room
import com.codingindia.pregnancyvitals.data.local.AppDatabase
import com.codingindia.pregnancyvitals.data.local.dao.VitalsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java, "pregnancy_tracker_db"
        ).build()
    }

    @Provides
    fun provideVitalsDao(database: AppDatabase): VitalsDao {
        return database.vitalsDao()
    }
}