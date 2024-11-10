package com.edumate.greenify.feature.plants.plantDetails

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.edumate.greenify.core.ui.model.PlantUI
import com.edumate.greenify.core.ui.model.toPlantUI
import com.edumate.greenify.core.ui.theme.GreenifyTheme
import com.edumate.greenify.feature.plants.plantsList.PlantScreenState
import com.edumate.greenify.feature.plants.plantsList.previewPlant
import com.example.greenify.feature.plants.R
import kotlinx.collections.immutable.toPersistentList

@Composable
fun PlantDetailsScreen(
    state: PlantScreenState,
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

                    PlanetImageWithNameOverlay(state.selectedPlant,
                        Modifier
                            .fillMaxSize()
                            .height(300.dp))

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
            Text("More Info", Modifier.padding(12.dp))
        }
    }
}

@Composable
fun TitledInfo(
    title: String,
    info: String,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = contentColor
        )
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(top = 12.dp)
        )

        Spacer(Modifier.size(4.dp))

        AnimatedContent(
            targetState = info,
            modifier = Modifier
                .align(
                    Alignment.Start
                )
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            label = "InfoAnimation"
        ) { formattedText ->
            Text(
                text = formattedText,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Normal,
                color = contentColor
            )
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
            modifier = Modifier
        )
    }
}