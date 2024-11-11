package com.edumate.greenify.feature.plants.plantDetails.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.edumate.greenify.core.ui.model.PlantUI
import com.example.greenify.feature.plants.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlanetImageWithNameOverlay(
    plant: PlantUI,
    modifier: Modifier = Modifier
) {
    Box(
        modifier,
        contentAlignment = Alignment.BottomStart
    ) {
        GlideImage(
            model = plant.imageUrl,
            contentDescription = plant.name,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp)),
            contentScale = ContentScale.FillBounds,
            loading = placeholder(resourceId = R.drawable.placeholder)
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 12.dp),
            text = plant.name,
            style = MaterialTheme.typography.displayMedium,
            color = Color.White
        )
    }
}