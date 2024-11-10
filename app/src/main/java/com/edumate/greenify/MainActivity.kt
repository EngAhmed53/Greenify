package com.edumate.greenify

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edumate.greenify.core.ui.theme.GreenifyTheme
import com.edumate.greenify.core.ui.util.ObserveAsEvents
import com.edumate.greenify.feature.plants.plantDetails.PlantDetailsScreen
import com.edumate.greenify.feature.plants.plantsList.PlantListScreen
import com.edumate.greenify.feature.plants.plantsList.PlantsListEvent
import com.edumate.greenify.feature.plants.plantsList.PlantsViewModel
import com.edumate.greenify.utils.toStringResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreenifyTheme {
                Scaffold { padding ->
                    val viewModel: PlantsViewModel = viewModel()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    val selectedPlant = state.selectedPlant
                    val context = LocalContext.current
                    ObserveAsEvents(events = viewModel.events) { event ->
                        when (event) {
                            is PlantsListEvent.Error -> {
                                Toast.makeText(
                                    context,
                                    event.error.toStringResource(),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }

                    when {
                        selectedPlant != null -> {
                            PlantDetailsScreen(
                                selectedPlant,
                                modifier = Modifier.padding(padding)
                            )
                        }

                        else -> {
                            PlantListScreen(
                                screenState = state,
                                countries = viewModel.getSupportedCountriesNames(),
                                onCountryFilterSelected = viewModel::changeCountryFilter,
                                loadMore = viewModel::loadPlants,
                                onPlantSelected = viewModel::onPlantSelected,
                                modifier = Modifier.padding(padding)
                            )
                        }
                    }
                }
            }
        }
    }
}