package com.edumate.greenify.core.model

data class Plant(
    val id: Int,
    val name: String,
    val year: String,
    val status: String,
    val family: String,
    val bibliography: String,
    val scientificName: String,
    val author: String,
    val imageUrl: String
)