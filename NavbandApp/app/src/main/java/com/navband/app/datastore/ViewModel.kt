package com.navband.app.datastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {
    val login: StateFlow<String> = dataStoreManager.tokenFlow.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        "" // valor inicial
    )

    val token: StateFlow<String> = dataStoreManager.tokenFlow.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        "" // valor inicial
    )

    fun saveLogin(login: String) {
        viewModelScope.launch {
            dataStoreManager.saveLogin(login)
        }
    }

    fun saveToken(token: String) {
        viewModelScope.launch {
            dataStoreManager.saveToken(token)
        }
    }

    fun clearToken() {
        viewModelScope.launch {
            dataStoreManager.clearToken()
        }
    }

    fun clearLogin() {
        viewModelScope.launch {
            dataStoreManager.clearLogin()
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            dataStoreManager.clearAll()
        }
    }
}
