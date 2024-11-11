package com.edumate.greenify.feature.plants.plantDetails.components

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.edumate.greenify.core.ui.model.PlantUI
import com.example.greenify.feature.plants.R

@Composable
fun PlantInfoList(
    plant: PlantUI,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val primaryColor = MaterialTheme.colorScheme.primary.toArgb()
    Column(
        modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TitledInfo(
            title = stringResource(R.string.feature_plants_family),
            info = plant.family,
            contentColor,
        )

        TitledInfo(
            title = stringResource(R.string.feature_plants_index),
            info = plant.index.asFormatedIndex(),
            contentColor,
        )
        TitledInfo(
            title = stringResource(R.string.feature_plants_author),
            info = plant.author,
            contentColor = contentColor,
        )

        ElevatedButton(
            onClick = { openLinkInCustomTab(context, plant.getMoreDetailsLink(), primaryColor) },
            Modifier
                .padding(vertical = 24.dp, horizontal = 12.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(stringResource(R.string.feature_plants_more_information), Modifier.padding(12.dp))
        }
    }
}

private fun openLinkInCustomTab(context: Context, url: String, color: Int) {
    val colorSchemeParams = CustomTabColorSchemeParams.Builder()
        .setToolbarColor(color)
        .build()

    val customTabsIntent = CustomTabsIntent.Builder()
        .setDefaultColorSchemeParams(colorSchemeParams)
        .setShowTitle(true)
        .build()

    customTabsIntent.launchUrl(context, Uri.parse(url))
}
