package com.mayrthomas.cryptoviewer.data

import com.mayrthomas.cryptoviewer.model.BaseCoin
import com.mayrthomas.cryptoviewer.storage.FavoritesDataStoreManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

class FavoriteRepositoryImpl(private val favoritesDataStoreManager: FavoritesDataStoreManager): FavoriteRepository {

    override fun getFavorites(): Flow<List<BaseCoin>> {
        return favoritesDataStoreManager.getFavoritesList()
            .catch {
                emit("")
            }
            .map { favorites ->
                try {
                    Json.decodeFromString<List<BaseCoin>>(favorites)
                } catch (ex: Exception) {
                    emptyList()
                }
            }
    }

    override suspend fun addToFavorites(coin: BaseCoin): Boolean = runBlocking {
        try {
            // get current favorites
            val current = favoritesDataStoreManager.getFavoritesList().first()
            val currentList = Json.decodeFromString<MutableList<BaseCoin>>(current)

            // add coin to list
            currentList.add(coin)

            // parse to string
            val coins = Json.encodeToString(currentList)

            // save new list
            return@runBlocking favoritesDataStoreManager.saveFavouritesList(coins)
        } catch (ex: Exception) {
            // if any operation throws any kind of exception the operation was not successful
            return@runBlocking false
        }
    }

    override suspend fun removeFromFavorites(coin: BaseCoin): Boolean {
        try {
            // get current favorites
            val current = favoritesDataStoreManager.getFavoritesList().first()
            val currentList = Json.decodeFromString<MutableList<BaseCoin>>(current)

            // remove coin from list if it exists
            if(!currentList.contains(coin)) return false

            currentList.remove(coin)

            // parse to string
            val coins = Json.encodeToString(currentList)

            // save new list
            return favoritesDataStoreManager.saveFavouritesList(coins)
        } catch (ex: Exception) {
            // if any operation throws any kind of exception the operation was not successful
            return false
        }
    }
}