package com.edumate.greenify.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edumate.greenify.core.common.NetworkError
import com.edumate.greenify.core.common.onError
import com.edumate.greenify.core.common.onSuccess
import com.edumate.greenify.core.domain.model.SupportedCountries
import com.edumate.greenify.core.domain.usecases.FetchPlantsUseCase
import com.edumate.greenify.core.ui.toPlantUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PlantsViewModel
@Inject
constructor(
    private val fetchPlantsUseCase: FetchPlantsUseCase
) : ViewModel() {

    private var nextPage: Int? = 1

    private val _state = MutableStateFlow(PlantScreenState())
    val state = _state
        .onStart { loadPlants() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            PlantScreenState()
        )


    fun getSupportedCountriesNames(): List<String> {
        return SupportedCountries.entries.map {
            it.name.lowercase(Locale.getDefault()).replaceFirstChar(Char::titlecase)
        }
    }

    fun changeCountryFilter(selectedCountryIndex: Int) {
        if (selectedCountryIndex == _state.value.countryIndex) return
        else {
            _state.update { it.copy(isLoading = false, countryIndex = selectedCountryIndex, plants = persistentListOf()) }
            nextPage = 1 // reset the page counter
            loadPlants()
        }
    }

    fun loadPlants() {
        viewModelScope.launch {
            if (state.value.isLoading) return@launch
            val page = nextPage ?: return@launch
            val country = SupportedCountries[_state.value.countryIndex]

            _state.update { it.copy(isLoading = true) }

            fetchPlantsUseCase(page, country)
                .onSuccess { loadedPlants ->
                    nextPage = nextPage?.plus(1) // Move to next page
                    _state.update {
                        it.copy(
                            isLoading = false,
                            plants = it.plants.addAll(loadedPlants.map { plant -> plant.toPlantUI() })
                        )
                    }
                }
                .onError { error ->
                    if (error == NetworkError.NOT_FOUND) {
                        nextPage = null // Reached last page
                    }
                    _state.update { it.copy(isLoading = false) }
                }
        }
    }
}