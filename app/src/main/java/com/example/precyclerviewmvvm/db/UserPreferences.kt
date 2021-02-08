package com.example.pprecyclerviewmvvm.db

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.clear
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferences @Inject constructor(context: Context){

    private val applicationContext = context.applicationContext
    private val dataStore : DataStore<Preferences>

    init {
        dataStore = applicationContext.createDataStore(
            name = "my_data_store"
        )
    }

    companion object {
        private val LAST_SAVED = preferencesKey<String>("key_last_saved")
    }

    val lastUpdatedAt: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[LAST_SAVED]
        }

    suspend fun saveLastUpdated(lastUpdatedAt: String) {
        dataStore.edit { preferences ->
            preferences[LAST_SAVED] = lastUpdatedAt
        }
    }

    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }



}