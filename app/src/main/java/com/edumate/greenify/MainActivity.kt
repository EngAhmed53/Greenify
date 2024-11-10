package com.edumate.greenify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edumate.greenify.core.ui.theme.GreenifyTheme
import com.edumate.greenify.feature.home.PlantListScreen
import com.edumate.greenify.feature.home.PlantsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreenifyTheme {
                val viewModel: PlantsViewModel = viewModel()
                val state by viewModel.state.collectAsStateWithLifecycle()
                PlantListScreen(
                    screenState = state,
                    countries = viewModel.getSupportedCountriesNames(),
                    onCountryFilterSelected = viewModel::changeCountryFilter,
                    loadMore = viewModel::loadPlants,
                    modifier = Modifier
                )
            }
        }
    }
}