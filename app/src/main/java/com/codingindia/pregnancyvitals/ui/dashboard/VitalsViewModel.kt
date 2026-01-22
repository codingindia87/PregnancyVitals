package com.codingindia.pregnancyvitals.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.codingindia.pregnancyvitals.data.local.entity.PregnancyVitals
import com.codingindia.pregnancyvitals.data.repository.VitalsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class VitalsViewModel @Inject constructor(
    private val repository: VitalsRepository
) : ViewModel() {
    val allVitals = repository.getAllVitals().asLiveData()

    fun addVitals(vitals: PregnancyVitals) {
        viewModelScope.launch {
            repository.insertVitals(vitals)
        }
    }
}