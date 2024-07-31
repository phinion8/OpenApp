package com.priyanshu.openapp.data.local.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.priyanshu.openapp.domain.repositories.PreferenceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_data")

class PreferenceManagerImpl(context: Context): PreferenceManager {

    private val dataStore = context.dataStore

    companion object {
        const val ON_BOARDING_PREFERENCES_KEY = "on_boarding_preferences"
        const val TOKEN_TIME_KEY = "token_time_key"
        const val TOKEN_KEY = "token_key"
    }

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(ON_BOARDING_PREFERENCES_KEY)
        val cachedTimeKey = longPreferencesKey(TOKEN_TIME_KEY)
        val tokenKey = stringPreferencesKey(TOKEN_KEY)
    }

    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
            onBoardingState
        }
    }

    override suspend fun saveAccessToken(token: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.tokenKey] = token
        }
    }

    override fun getAccessToken(): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val tokenState = preferences[PreferencesKey.tokenKey] ?: ""
            tokenState
        }
    }

    override suspend fun saveLastTokenAccessTime(time: Long) {
        dataStore.edit { preferences->

            preferences[PreferencesKey.cachedTimeKey] = time

        }
    }

    override fun getLastTokenAccessTime(): Flow<Long> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val tokenAccessTimeState = preferences[PreferencesKey.cachedTimeKey] ?: 0
            tokenAccessTimeState
        }
    }
}