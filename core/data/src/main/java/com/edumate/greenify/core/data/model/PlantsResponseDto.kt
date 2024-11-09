package com.edumate.greenify.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PlantsResponseDto(
    val data: List<PlantDto>
)