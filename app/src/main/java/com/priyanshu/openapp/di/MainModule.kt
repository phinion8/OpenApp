package com.priyanshu.openapp.di

import com.priyanshu.openapp.data.local.repositories.MainRepositoryImpl
import com.priyanshu.openapp.data.remote.ApiService
import com.priyanshu.openapp.domain.repositories.MainRepository
import com.priyanshu.openapp.domain.usecases.MainUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        apiService: ApiService
    ) : MainRepository {
        return MainRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideMainUseCases(
        mainRepository: MainRepository
    ): MainUseCase{
        return MainUseCase(mainRepository)
    }

}