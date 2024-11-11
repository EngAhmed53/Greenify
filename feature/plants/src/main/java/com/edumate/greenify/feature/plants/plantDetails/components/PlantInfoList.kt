package com.edumate.greenify.feature.plants.plantDetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            onClick = { },
            Modifier
                .padding(vertical = 24.dp, horizontal = 12.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(stringResource(R.string.feature_plants_more_information), Modifier.padding(12.dp))
        }
    }
}
