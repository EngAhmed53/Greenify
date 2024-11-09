package com.edumate.greenify.core.data.di

import com.edumate.greenify.core.data.datasource.PlantRemoteDataSource
import com.edumate.greenify.core.data.repositpry.PlantsRepository
import com.edumate.greenify.core.data.repositpry.PlantsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {
    @Singleton
    @Provides
    fun providePlantsRepository(plantRemoteDataSource: PlantRemoteDataSource): PlantsRepository =
        PlantsRepositoryImpl(plantRemoteDataSource)
}