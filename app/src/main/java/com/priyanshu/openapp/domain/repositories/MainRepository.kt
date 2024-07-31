package com.priyanshu.openapp.domain.repositories

import com.priyanshu.openapp.data.remote.ApiService
import com.priyanshu.openapp.domain.models.AppResult
import javax.inject.Inject

interface MainRepository {

    suspend fun getAppResult(): AppResult

}