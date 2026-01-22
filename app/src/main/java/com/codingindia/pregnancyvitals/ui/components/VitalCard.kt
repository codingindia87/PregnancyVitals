package com.codingindia.pregnancyvitals.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.codingindia.pregnancyvitals.R
import com.codingindia.pregnancyvitals.data.local.entity.PregnancyVitals
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale.getDefault

@Composable
fun VitalCard(vital: PregnancyVitals) {

    val formattedDate = remember(vital.date) {
        val sdf =
            SimpleDateFormat("EEE, dd MMM yyyy hh:mm a", getDefault())
        sdf.format(Date(vital.date))
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF3E5F5))
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    VitalItem(
                        iconRes = R.drawable.heart_rate, value = "${vital.heartRate} bpm"
                    )
                    VitalItem(
                        iconRes = R.drawable.scale, value = "${vital.weight} kg"
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    VitalItem(
                        iconRes = R.drawable.blood_pressure,
                        value = "${vital.systolicBP}/${vital.diastolicBP} mmHg"
                    )
                    VitalItem(
                        iconRes = R.drawable.newborn, value = "${vital.babyKick} kicks"
                    )
                }
            }

            Surface(
                modifier = Modifier.fillMaxWidth(), color = Color(0xFF9C27B0)
            ) {
                Text(
                    text = formattedDate,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun VitalItem(iconRes: Int, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color(0xFF4A148C),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = Color(0xFF4A148C)
        )
    }
}