package com.mayrthomas.cryptoviewer.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.mayrthomas.cryptoviewer.BuildConfig
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class UserPreferencesDataStoreManager(private val context: Context) {

    private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = BuildConfig.FAVORITES_PREFS_FILE_NAME)

    companion object {
        var CURRENCY_KEY = stringPreferencesKey("currency")
    }

    init {
        runBlocking {
            context.preferencesDataStore.edit { preferences ->
                preferences[CURRENCY_KEY] = "usd"
            }
        }
    }

    suspend fun getStringPreference(key: Preferences.Key<String>): String {
        return context.preferencesDataStore.data.catch { exception ->
            if(exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.first()[key] ?: throw NoSuchElementException()
    }
}