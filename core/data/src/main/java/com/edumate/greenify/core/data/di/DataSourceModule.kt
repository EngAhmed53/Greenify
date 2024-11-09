package com.edumate.greenify.core.data.di

import com.edumate.greenify.core.data.datasource.PlantRemoteDataSource
import com.edumate.greenify.core.data.datasource.PlantRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceModule {
    @Singleton
    @Provides
    fun providePlantsRemoteDataSource(httpClient: HttpClient): PlantRemoteDataSource =
        PlantRemoteDataSourceImpl(httpClient)
}