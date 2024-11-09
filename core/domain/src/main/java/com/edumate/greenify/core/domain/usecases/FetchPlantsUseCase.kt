package com.edumate.greenify.core.domain.usecases

import com.edumate.greenify.core.common.NetworkError
import com.edumate.greenify.core.common.Result
import com.edumate.greenify.core.data.repositpry.PlantsRepository
import com.edumate.greenify.core.domain.model.Plant
import com.edumate.greenify.core.domain.model.SupportedCountries
import com.edumate.greenify.core.domain.model.toPlant
import javax.inject.Inject


class FetchPlantsUseCase
@Inject
constructor(
    private val plantsRepository: PlantsRepository
) {
    suspend operator fun invoke(page: Int, country: SupportedCountries): Result<List<Plant>, NetworkError> {
        val result = when (country) {
            SupportedCountries.ALL -> {
                plantsRepository.fetchAllPlants(page)
            }
            else -> {
                plantsRepository.fetchPlantsByCountry(page, country.code)
            }
        }

        return when (result) {
            is Result.Success -> Result.Success(result.data.map { it.toPlant() })
            is Result.Error -> result
        }
    }
}