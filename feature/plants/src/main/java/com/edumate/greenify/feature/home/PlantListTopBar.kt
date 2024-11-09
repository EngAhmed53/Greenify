package com.edumate.greenify.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.edumate.greenify.core.model.SupportedCountries
import com.edumate.greenify.core.ui.SelectableFilterCountry
import com.example.compose.GreenifyTheme

@Composable
fun PlantListTopBar(
    countries: List<SelectableFilterCountry>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(countries) { country ->
            var selected by remember { country.selected }

            FilterChip(
                onClick = { selected = !selected },
                label = {
                    Text(country.name)
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
            countries = SupportedCountries.entries.map {
                if (it == SupportedCountries.ALL)
                    SelectableFilterCountry(name = it.name, selected = mutableStateOf(true))
                else SelectableFilterCountry(name = it.name)
            },
            Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}