package com.edumate.greenify.core.domain.model

import com.edumate.greenify.core.data.model.PlantDto

data class Plant(
    val id: Int,
    val name: String?,
    val year: Int,
    val status: String,
    val family: String?,
    val bibliography: String?,
    val scientificName: String?,
    val author: String?,
    val imageUrl: String
)

fun PlantDto.toPlant(): Plant {
    return Plant(
        id = id,
        name = name,
        year = year,
        status = status,
        family = family,
        bibliography = bibliography,
        scientificName = scientificName,
        author = author,
        imageUrl = imageUrl
    )
}