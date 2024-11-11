package com.edumate.greenify.navigation

import android.widget.Toast
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edumate.greenify.core.ui.util.ObserveAsEvents
import com.edumate.greenify.feature.plants.plantDetails.PlantDetailsScreen
import com.edumate.greenify.feature.plants.plantsList.PlantListScreen
import com.edumate.greenify.feature.plants.plantsList.PlantsViewModel
import com.edumate.greenify.feature.plants.plantsList.core.PlantsListEvent
import com.edumate.greenify.utils.toStringResource

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AdaptiveCoinListDetailPane(
    modifier: Modifier = Modifier,
    viewModel: PlantsViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
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

    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    val plantsListState = rememberLazyListState()
    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = {
            AnimatedPane {
                PlantListScreen(
                    screenState = state,
                    countries = viewModel.getSupportedCountriesNames(),
                    onCountryFilterSelected = viewModel::changeCountryFilter,
                    loadMore = viewModel::loadPlants,
                    onPlantSelected = { plant ->
                        viewModel.onPlantSelected(plant)
                        navigator.navigateTo(pane = ListDetailPaneScaffoldRole.Detail)
                    },
                    listState = plantsListState
                )
            }
        },
        detailPane = {
            AnimatedPane {
                PlantDetailsScreen(
                    state = state,
                    canNavigateBack = navigator.canNavigateBack(),
                    onBackPressedCallback = {
                        navigator.navigateBack()
                    }
                )
            }
        },
        modifier = modifier
    )
}