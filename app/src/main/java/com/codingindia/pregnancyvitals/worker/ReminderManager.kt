package com.codingindia.pregnancyvitals.worker

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object ReminderManager {
    fun startReminder(context: Context) {
        val reminderRequest = PeriodicWorkRequestBuilder<VitalReminderWorker>(
            5, TimeUnit.HOURS
        ).build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "VitalsReminder",
            ExistingPeriodicWorkPolicy.REPLACE,
            reminderRequest
        )
    }
}