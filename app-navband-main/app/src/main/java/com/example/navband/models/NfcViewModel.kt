package com.example.navband.models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NfcViewModel : ViewModel() {
    private val _nfcEnabled = MutableStateFlow(false)
    val nfcEnabled: StateFlow<Boolean> = _nfcEnabled

    private val _tagData = MutableStateFlow<String?>(null)
    val tagData: StateFlow<String?> = _tagData

    fun startListening() {
        _nfcEnabled.value = true
    }

    fun stopListening() {
        _nfcEnabled.value = false
    }

    fun onTagScanned(id: String) {
        _tagData.value = id
    }
}