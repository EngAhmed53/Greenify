package com.edumate.greenify.core.domain.model

enum class SupportedCountries(val code: String) {
    ALL("all"),
    PALESTINE("pal"),
    SUDAN("sud"),
    MYANMAR("mya"),
    TRANSCAUCASIA("tcs"),
    UZBEKISTAN("uzb");

    companion object {
        operator fun get(index: Int): SupportedCountries = entries.getOrNull(index) ?: throw IllegalArgumentException("Invalid country index")
    }
}