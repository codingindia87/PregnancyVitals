package com.codingindia.pregnancyvitals

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.codingindia.pregnancyvitals.ui.dashboard.TrackMyPregnancyScreen
import com.codingindia.pregnancyvitals.ui.theme.PregnancyVitalsTheme
import com.codingindia.pregnancyvitals.worker.ReminderManager
import com.codingindia.pregnancyvitals.worker.VitalReminderWorker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        ReminderManager.startReminder(this)

        setContent {
            PregnancyVitalsTheme {
                val context = LocalContext.current

                val permissionLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission()
                ) { isGranted ->

                }

                LaunchedEffect(Unit) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        val isPermissionGranted = ContextCompat.checkSelfPermission(
                            context, Manifest.permission.POST_NOTIFICATIONS
                        ) == PackageManager.PERMISSION_GRANTED

                        if (!isPermissionGranted) {
                            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        }
                    }
                }

                Surface(modifier = Modifier.fillMaxSize()) {
                    TrackMyPregnancyScreen()
                }
            }
        }
    }

    // Use only for testing worker notification
    fun testNow(context: Context) {
        val testRequest = OneTimeWorkRequestBuilder<VitalReminderWorker>().build()
        WorkManager.getInstance(context).enqueue(testRequest)
    }
}