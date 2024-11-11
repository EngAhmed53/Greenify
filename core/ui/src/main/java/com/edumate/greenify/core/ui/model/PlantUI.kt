package com.edumate.greenify.core.ui.model

import com.edumate.greenify.core.domain.model.Plant

data class PlantUI(
    val id: Int,
    val name: String,
    val year: Int,
    val status: String,
    val family: String,
    val index: PlantIndex,
    val author: String,
    val imageUrl: String?,
) {
    fun getMoreDetailsLink(): String {
        return "https://en.wikipedia.org/wiki/${name}"
    }
}

data class PlantIndex(
    val bibliography: String?,
    val scientificName: String?,
) {
    fun asFormatedIndex(): String {
        return when {
            bibliography == null && scientificName == null -> "N/A"
            bibliography != null && scientificName == null -> bibliography
            bibliography == null && scientificName != null -> scientificName
            else -> "$bibliography\n$scientificName"
        }
    }
}

fun Plant.toPlantUI(): PlantUI {
    return PlantUI(
        id,
        name ?: "N/A",
        year,
        status,
        family ?: "N/A",
        PlantIndex(bibliography, scientificName),
        author ?: "N/A",
        imageUrl
    )
}