package com.edumate.greenify.feature.plants.plantsList.core

import com.edumate.greenify.core.common.NetworkError

sealed interface PlantsListEvent {
    data class Error(val error: NetworkError) : PlantsListEvent
}