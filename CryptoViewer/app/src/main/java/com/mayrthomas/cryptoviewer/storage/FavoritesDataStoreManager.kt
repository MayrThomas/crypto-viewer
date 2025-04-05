package com.mayrthomas.cryptoviewer.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.mayrthomas.cryptoviewer.BuildConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class FavoritesDataStoreManager(private val context: Context) {
    private val Context.favoritesDataStore: DataStore<Preferences> by preferencesDataStore(name = BuildConfig.FAVORITES_PREFS_FILE_NAME)

    companion object {
        private var favoritesList = stringPreferencesKey("favorites")
    }

    fun getFavoritesList(): Flow<String> {
        return context.favoritesDataStore.data.catch {
            emit(emptyPreferences())
        }.map { preferences: Preferences ->
            preferences[favoritesList] ?: ""
        }
    }

    suspend fun saveFavouritesList(coins: String): Boolean {
        try {
            context.favoritesDataStore.edit { mutablePreferences: MutablePreferences ->
                mutablePreferences[favoritesList] = coins
            }
            return true
        } catch (ex: Exception) {
            return false
        }
    }
}