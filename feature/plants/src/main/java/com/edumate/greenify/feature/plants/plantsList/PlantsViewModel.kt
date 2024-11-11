package com.edumate.greenify.feature.plants.plantsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edumate.greenify.core.common.NetworkError
import com.edumate.greenify.core.common.onError
import com.edumate.greenify.core.common.onSuccess
import com.edumate.greenify.core.domain.model.Plant
import com.edumate.greenify.core.domain.model.SupportedCountries
import com.edumate.greenify.core.domain.usecases.FetchPlantsUseCase
import com.edumate.greenify.core.ui.model.PlantUI
import com.edumate.greenify.core.ui.model.toPlantUI
import com.edumate.greenify.feature.plants.plantsList.core.PlantScreenState
import com.edumate.greenify.feature.plants.plantsList.core.PlantsListEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
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

    private val _events = Channel<PlantsListEvent>()
    val events = _events.receiveAsFlow()

    private val _state = MutableStateFlow(PlantScreenState())
    val state = _state
        .onStart { loadPlants() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            PlantScreenState()
        )

    private val cachedPlantsPages = mutableMapOf<String, Pair<List<Plant>, Int>>()

    fun getSupportedCountriesNames(): List<String> {
        return SupportedCountries.entries.map {
            it.name.lowercase(Locale.getDefault()).replaceFirstChar(Char::titlecase)
        }
    }

    fun changeCountryFilter(selectedCountryIndex: Int) {
        if (selectedCountryIndex == _state.value.countryIndex) return
        else {
            val country = SupportedCountries[selectedCountryIndex]
            val (cachedPlantsList, _) = cachedPlantsPages.getOrDefault(country.code, emptyList<Plant>() to 1)

            _state.update {
                it.copy(
                    isLoading = false,
                    countryIndex = selectedCountryIndex,
                    plants = cachedPlantsList.map { it.toPlantUI() }.toPersistentList()
                )
            }

            // Load plants list for selected country, if no cached plants for it
            // Otherwise, wait until the user manually fetch new data
            if (cachedPlantsList.isEmpty()) {
                loadPlants()
            }
        }
    }

    fun onPlantSelected(plantUI: PlantUI) {
        _state.update { it.copy(selectedPlant = plantUI) }
    }

    fun loadPlants() {
        viewModelScope.launch {
            if (state.value.isLoading) return@launch

            val country = SupportedCountries[_state.value.countryIndex]
            val (cachedPlantsList, page) = cachedPlantsPages.getOrDefault(country.code, emptyList<Plant>() to 1)

            if (page == Int.MAX_VALUE) return@launch // Reached last page in the current country

            _state.update { it.copy(isLoading = true) }

            fetchPlantsUseCase(page, country)
                .onSuccess { loadedPlants ->

                    // Cache new data
                    val nextPage = page + 1
                    val newPlantsList = cachedPlantsList + loadedPlants
                    cachedPlantsPages[country.code] = newPlantsList to nextPage

                    _state.update {
                        it.copy(
                            isLoading = false,
                            plants = it.plants.addAll(loadedPlants.map { plant -> plant.toPlantUI() })
                        )
                    }
                }
                .onError { error ->
                    if (error == NetworkError.NOT_FOUND) {
                        // Reached last page of current country, set next page to infinity
                        val nextPage = Int.MAX_VALUE
                        cachedPlantsPages[country.code] = cachedPlantsList to nextPage
                    }
                    _state.update { it.copy(isLoading = false) }
                    _events.send(PlantsListEvent.Error(error))
                }
        }
    }
}