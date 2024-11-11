package com.edumate.greenify.feature.plants.plantsList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.edumate.greenify.core.domain.model.Plant
import com.edumate.greenify.core.ui.model.PlantUI
import com.edumate.greenify.core.ui.model.toPlantUI
import com.edumate.greenify.core.ui.theme.GreenifyTheme
import com.example.greenify.feature.plants.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlantListItem(
    plantUI: PlantUI,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12),
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GlideImage(
                model = plantUI.imageUrl,
                contentDescription = plantUI.name,
                modifier = Modifier
                    .padding(start = 16.dp, top = 12.dp, bottom = 12.dp)
                    .size(80.dp)
                    .clip(RoundedCornerShape(20)),
                contentScale = ContentScale.FillBounds,
                loading = placeholder(resourceId = R.drawable.placeholder)
            )

            Column(
                Modifier
                    .weight(1f)
                    .padding(top = 12.dp, bottom = 12.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = plantUI.year.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Light,
                    color = contentColor
                )
                Text(
                    text = plantUI.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Medium,
                    color = contentColor
                )
                Text(
                    text = plantUI.status,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Light,
                    color = contentColor
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PlantListItemPreview() {
    GreenifyTheme {
        PlantListItem(
            plantUI = previewPlant.toPlantUI(),
            onClick = {},
            Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}

internal val previewPlant = Plant(
    id = 1,
    name = "Cuda",
    year = 1991,
    status = "Alive",
    family = "Orcidia",
    bibliography = "PlantX",
    scientificName = "The X Plant",
    author = "Dev Ahmed",
    imageUrl = "https://bs.plantnet.org/image/o/94ed4fa4f55710b452e624de45faec9b17c5198b"
)