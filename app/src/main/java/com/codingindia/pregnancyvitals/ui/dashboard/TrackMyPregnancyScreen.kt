package com.codingindia.pregnancyvitals.ui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codingindia.pregnancyvitals.data.local.entity.PregnancyVitals
import com.codingindia.pregnancyvitals.ui.components.AddVitalsDialog
import com.codingindia.pregnancyvitals.ui.components.VitalCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackMyPregnancyScreen(
    viewModel: VitalsViewModel = hiltViewModel()
) {
    var showAddVitalsDialog by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    "Track My Pregnancy", style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary
                    )
                )
            })

    }, floatingActionButton = {
        FloatingActionButton(
            onClick = { showAddVitalsDialog = true },
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Vitals")
        }
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val allVitals by viewModel.allVitals.observeAsState(initial = emptyList())

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(bottom = 80.dp) // FAB के लिए जगह
            ) {
                items(allVitals) { vital ->
                    VitalCard(vital = vital)
                }
            }

            // Add Vitals Dialog
            if (showAddVitalsDialog) {
                AddVitalsDialog(
                    onDismiss = { showAddVitalsDialog = false },
                    onVitalsAdded = { hr, wt, sys, dia, babyKicks ->
                        viewModel.addVitals(
                            PregnancyVitals(
                                date = System.currentTimeMillis(),
                                weight = wt.toDoubleOrNull() ?: 0.0,
                                heartRate = hr.toIntOrNull() ?: 0,
                                systolicBP = sys.toIntOrNull() ?: 0,
                                diastolicBP = dia.toIntOrNull() ?: 0,
                                babyKick = babyKicks.toIntOrNull() ?: 0
                            )
                        )
                        showAddVitalsDialog = false
                    })
            }
        }

    }
}
