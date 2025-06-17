package com.navband.app.datastore

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.navband.app.datastore.DataStoreManager

class UserViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            val dataStoreManager = DataStoreManager(context.applicationContext)
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(dataStoreManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
