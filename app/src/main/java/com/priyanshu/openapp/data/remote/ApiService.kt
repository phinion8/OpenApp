package com.priyanshu.openapp.data.remote

import com.priyanshu.openapp.domain.models.AppResult
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService{

    @GET("api/v1/dashboardNew")
    suspend fun getAppResult(
        @Header ("Authorization") token: String
    ): AppResult

}