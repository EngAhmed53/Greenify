package com.edumate.greenify.core.data.repositpry

import com.edumate.greenify.core.data.datasource.PlantRemoteDataSource
import com.edumate.greenify.core.data.model.PlantDto
import com.edumate.greenify.core.common.Result
import com.edumate.greenify.core.common.NetworkError
import javax.inject.Inject

internal class PlantsRepositoryImpl
@Inject
constructor(
    private val plantRemoteDataSource: PlantRemoteDataSource
) : PlantsRepository {
    override suspend fun fetchAllPlants(page: Int): Result<List<PlantDto>, NetworkError> {
        return plantRemoteDataSource.fetchAllPlants(page)
    }

    override suspend fun fetchPlantsByCountry(page: Int, countryCode: String): Result<List<PlantDto>, NetworkError> {
        return plantRemoteDataSource.fetchPlantsByCountry(page, countryCode)
    }
}