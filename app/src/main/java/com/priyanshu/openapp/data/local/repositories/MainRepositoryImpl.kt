package com.priyanshu.openapp.data.local.repositories

import com.priyanshu.openapp.data.remote.ApiService
import com.priyanshu.openapp.domain.models.AppResult
import com.priyanshu.openapp.domain.repositories.MainRepository
import com.priyanshu.openapp.utils.Constants
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): MainRepository {
    override suspend fun getAppResult(): AppResult {
        return apiService.getAppResult(
            token = Constants.TOKEN
        )
    }
}