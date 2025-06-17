package com.example.datastore.datastore

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

object UserPreference {
    private val EMAIL_KEY = stringPreferencesKey("email_key")
    private val TELEFONE_KEY = stringPreferencesKey("tel_key")


    suspend fun saveEmail(context: Context, email: String) {
        context.dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = email
        }
    }

    suspend fun saveTelefone(context: Context, telefone: String) {
        context.dataStore.edit { preferences ->
            preferences[TELEFONE_KEY] = telefone
        }
    }

    fun getEmail(context: Context): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[EMAIL_KEY] ?: ""
        }
    }

    fun getTelefone(context: Context): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[TELEFONE_KEY] ?: ""
        }
    }
}