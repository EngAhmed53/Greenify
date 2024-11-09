package com.edumate.greenify.core.data.datasource

import com.edumate.greenify.core.data.model.PlantDto
import com.edumate.greenify.core.data.model.PlantsResponseDto
import com.edumate.greenify.core.common.Result
import com.edumate.greenify.core.common.map
import com.edumate.greenify.core.common.NetworkError
import com.edumate.greenify.core.network.utils.constructUrl
import com.edumate.greenify.core.network.utils.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject

internal class PlantRemoteDataSourceImpl
@Inject
constructor(
    private val httpClient: HttpClient
) : PlantRemoteDataSource {
    override suspend fun fetchAllPlants(page: Int): Result<List<PlantDto>, NetworkError> {
        return safeCall<PlantsResponseDto> { token ->
            httpClient.get {
                url(constructUrl("/plants"))
                parameter("token", token)
            }
        }.map { response ->
            response.data
        }
    }

    override suspend fun fetchPlantsByCountry(page: Int, countryCode: String): Result<List<PlantDto>, NetworkError> {
        return safeCall<PlantsResponseDto> { token ->
            httpClient.get {
                url(constructUrl("/distributions/${countryCode}/plants"))
                parameter("token", token)
            }
        }.map { response ->
            response.data
        }
    }
}