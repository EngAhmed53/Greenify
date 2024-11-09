package com.edumate.greenify.feature.home

import com.edumate.greenify.core.ui.PlantUI
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class PlantScreenState(
    val isLoading: Boolean = false,
    val plants: PersistentList<PlantUI> = persistentListOf(),
    val countryIndex: Int = 0,
    val selectedPlant: PlantUI? = null
)