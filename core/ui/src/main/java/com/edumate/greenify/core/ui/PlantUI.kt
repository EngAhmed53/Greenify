package com.edumate.greenify.core.ui

import com.edumate.greenify.core.model.Plant

data class PlantUI(
    val id: Int,
    val name: String,
    val year: String,
    val status: String,
    val family: String,
    val index: PlantIndex,
    val author: String,
    val imageUrl: String,
) {
}

data class PlantIndex(
    val bibliography: String,
    val scientificName: String,
) {
    fun asFormatedIndex(): String {
        return bibliography + "\n" + scientificName
    }
}

fun Plant.toPlantUI(): PlantUI {
    return PlantUI(
        id,
        name,
        year,
        status,
        family,
        PlantIndex(bibliography, scientificName),
        author,
        imageUrl
    )
}