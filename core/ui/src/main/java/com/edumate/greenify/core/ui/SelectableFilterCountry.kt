package com.edumate.greenify.core.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class SelectableFilterCountry(
    val name: String,
    val selected: MutableState<Boolean> = mutableStateOf(false),
)