package com.edumate.greenify.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlantDto(
    @SerialName("id")
    val id: Int,

    @SerialName("common_name")
    val name: String?,

    @SerialName("year")
    val year: Int,

    @SerialName("status")
    val status: String,

    @SerialName("family")
    val family: String?,

    @SerialName("bibliography")
    val bibliography: String?,

    @SerialName("scientific_name")
    val scientificName: String?,

    @SerialName("author")
    val author: String?,

    @SerialName("image_url")
    val imageUrl: String
)
