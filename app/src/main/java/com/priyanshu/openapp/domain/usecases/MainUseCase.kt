package com.priyanshu.openapp.domain.usecases

import coil.network.HttpException
import com.priyanshu.openapp.domain.models.AppResult
import com.priyanshu.openapp.domain.repositories.MainRepository
import com.priyanshu.openapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    fun getAppResult(): Flow<Resource<AppResult>> = flow<Resource<AppResult>> {
        emit(Resource.Loading())
        try {
            val response = mainRepository.getAppResult()
            emit(Resource.Success(response))
        } catch (e: IOException) {
            emit(Resource.Error("Something went wrong, please check your internet connection..."))
        } catch (e: HttpException) {
            emit(Resource.Error("Something went wrong!"))
        }
    }.catch {
        emit(Resource.Error(it.message))
    }.flowOn(Dispatchers.IO)

}