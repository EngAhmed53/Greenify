package com.edumate.greenify.feature.plants.plantDetails

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.edumate.greenify.core.ui.model.toPlantUI
import com.edumate.greenify.core.ui.theme.GreenifyTheme
import com.edumate.greenify.feature.plants.plantDetails.components.PlanetImageWithNameOverlay
import com.edumate.greenify.feature.plants.plantDetails.components.PlantInfoList
import com.edumate.greenify.feature.plants.plantsList.components.previewPlant
import com.edumate.greenify.feature.plants.plantsList.core.PlantScreenState
import com.example.greenify.feature.plants.R
import kotlinx.collections.immutable.toPersistentList

@Composable
fun PlantDetailsScreen(
    state: PlantScreenState,
    canNavigateBack: Boolean,
    onBackPressedCallback: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    Surface(modifier.fillMaxSize()) {
        when {
            state.selectedPlant != null -> {
                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        PlanetImageWithNameOverlay(
                            state.selectedPlant,
                            Modifier
                                .fillMaxSize()
                                .height(300.dp)
                        )

                        if (canNavigateBack) {
                            IconButton(
                                onClick = { onBackPressedCallback() },
                                modifier = Modifier
                                    .padding(top = 24.dp, start = 8.dp)
                                    .size(48.dp)
                                    .align(Alignment.TopStart),
                            ) {
                                Icon(
                                    Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                    contentDescription = "back",
                                    tint = Color.White,
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                        }
                    }

                    PlantInfoList(state.selectedPlant, contentColor)
                }
            }

            else -> {
                Box(
                    modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.feature_plants_select_plant_to_display_its_information),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PlantDetailsPreview() {
    GreenifyTheme {
        PlantDetailsScreen(
            state = PlantScreenState(
                isLoading = false,
                plants = (1..100).map { previewPlant.copy(id = it).toPlantUI() }.toPersistentList(),
                selectedPlant = previewPlant.toPlantUI()
            ),
            onBackPressedCallback = {},
            canNavigateBack = true,
            modifier = Modifier
        )
    }
}