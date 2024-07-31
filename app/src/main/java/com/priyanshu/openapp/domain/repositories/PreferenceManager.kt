package com.priyanshu.openapp.domain.repositories

import kotlinx.coroutines.flow.Flow

interface PreferenceManager {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
    suspend fun saveAccessToken(token: String)
    fun getAccessToken(): Flow<String>
    suspend fun saveLastTokenAccessTime(time: Long)
    fun getLastTokenAccessTime(): Flow<Long>
}