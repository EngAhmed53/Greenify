package com.edumate.greenify.feature.plants.plantsList

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.edumate.greenify.core.ui.theme.GreenifyTheme

@Composable
fun PlantListTopBar(
    countries: List<String>,
    selectedCountryIndex: Int,
    onCountrySelected: (index: Int) -> Unit,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
        state = listState
    ) {
        Log.d("PlantListTopBar", "Recomposed here, selected = $selectedCountryIndex")
        itemsIndexed(countries) { index, country ->
            Log.d("PlantListTopBar", "Recomposed index = $index")
            val selected = selectedCountryIndex == index
            FilterChip(
                onClick = {
                    onCountrySelected(index)
                },
                label = {
                    Text(country)
                },
                selected = selected,
                leadingIcon = if (selected) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done icon",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    null
                },
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun PlantListItemPreview() {
    GreenifyTheme {
        PlantListTopBar(
            countries = listOf("USA", "Egypt", "Palestine", "Qatar"),
            selectedCountryIndex = 0,
            onCountrySelected = {},
            listState = rememberLazyListState(),
            Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}