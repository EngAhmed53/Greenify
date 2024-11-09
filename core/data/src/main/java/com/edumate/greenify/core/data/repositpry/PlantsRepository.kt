package com.edumate.greenify.core.data.repositpry

import com.edumate.greenify.core.data.model.PlantDto
import com.edumate.greenify.core.common.Result
import com.edumate.greenify.core.common.NetworkError

interface PlantsRepository {
    suspend fun fetchAllPlants(page: Int): Result<List<PlantDto>, NetworkError>
    suspend fun fetchPlantsByCountry(page: Int, countryCode: String): Result<List<PlantDto>, NetworkError>
}