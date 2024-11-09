package com.edumate.greenify.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.edumate.greenify.core.model.SupportedCountries
import com.edumate.greenify.core.ui.SelectableFilterCountry
import com.edumate.greenify.core.ui.toPlantUI
import com.example.compose.GreenifyTheme

@Composable
fun PlantListScreen(
    countries: List<SelectableFilterCountry>,
    state: PlantScreenState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = { PlantListTopBar(countries) }
    ) { padding ->
        if (state.isLoading) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = modifier.padding(top = padding.calculateTopPadding()),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp),
            ) {
                items(items = state.plants, key = { it.id }) { plant ->
                    PlantListItem(
                        plant,
                        onClick = {},
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}


@PreviewLightDark
@Composable
private fun PlantScreenPreview() {
    GreenifyTheme {
        PlantListScreen(
            countries = SupportedCountries.entries.map {
                if (it == SupportedCountries.ALL)
                    SelectableFilterCountry(name = it.name, selected = mutableStateOf(true))
                else SelectableFilterCountry(name = it.name)
            },
            state = PlantScreenState(
                isLoading = false,
                plants = (1..100).map { previewPlant.copy(id = it).toPlantUI() }
            ),
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}
