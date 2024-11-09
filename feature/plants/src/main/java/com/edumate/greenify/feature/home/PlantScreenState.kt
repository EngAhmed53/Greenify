package com.edumate.greenify.feature.home

import androidx.compose.runtime.Immutable
import com.edumate.greenify.core.ui.PlantUI

@Immutable
data class PlantScreenState(
    val isLoading: Boolean = false,
    val plants: List<PlantUI> = emptyList(),
    val selectedPlant: PlantUI? = null
)