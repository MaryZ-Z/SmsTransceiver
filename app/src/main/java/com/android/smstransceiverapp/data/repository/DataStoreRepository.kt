package com.android.smstransceiverapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    coroutineScope: CoroutineScope
) {
    val isTelegramEnabled = dataStore.data.map { preferences ->
        preferences[TELEGRAM] ?: DEFAULT_BOOLEAN_VALUE
    }.stateIn(
        scope = coroutineScope,
        initialValue = DEFAULT_BOOLEAN_VALUE,
        started = SharingStarted.Eagerly
    )

    suspend fun saveTelegram(value: Boolean) = dataStore.edit { preferences ->
        preferences[TELEGRAM] = value
    }

    fun getTelegram() = runBlocking { dataStore.data.first()[TELEGRAM] ?: DEFAULT_BOOLEAN_VALUE }

    companion object {
        private const val DEFAULT_BOOLEAN_VALUE = false
        private val TELEGRAM = booleanPreferencesKey("telegram")
    }
}