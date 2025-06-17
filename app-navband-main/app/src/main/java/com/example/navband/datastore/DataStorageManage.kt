package com.example.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreManager(private val context: Context) {

    companion object {
        val LOGIN_KEY = stringPreferencesKey("login")
        val TOKEN_KEY = stringPreferencesKey("auth_token")
    }

    suspend fun saveLogin(login: String) {
        context.dataStore.edit { preferences ->
            preferences[LOGIN_KEY] = login
        }
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    val loginFlow: Flow<String> = context.dataStore.data
        .map { preferences -> preferences[LOGIN_KEY] ?: "" }

    val tokenFlow: Flow<String> = context.dataStore.data
        .map { preferences -> preferences[TOKEN_KEY] ?: "" }

    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }

    suspend fun clearLogin() {
        context.dataStore.edit { preferences ->
            preferences.remove(LOGIN_KEY)
        }
    }

    suspend fun clearAll() {
        context.dataStore.edit { it.clear() }
    }
}
