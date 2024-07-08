package com.muradakhundov.jetflix.common.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManager @Inject constructor(private val dataStore: DataStore<Preferences>) {
    companion object {
        const val DATA = "JetFlix"
    }

    fun getBoolean(key: String): Flow<Boolean> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map { preference ->
            preference[booleanPreferencesKey(key)] ?: false
        }
    }

    suspend fun setBoolean(key: String, isUserLoggedIn: Boolean) {
        dataStore.edit { preference ->
            preference[booleanPreferencesKey(key)] = isUserLoggedIn
        }
    }

}