package com.edumate.greenify.feature.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.example.compose.GreenifyTheme

@Composable
fun PlantListTopBar(
    countries: List<String>,
    selectedCountryIndex: Int,
    onCountrySelected: (index: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
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
            countries = listOf("USA", "Egypt", "Palastine", "Qatar"),
            selectedCountryIndex = 0,
            onCountrySelected = {},
            Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}