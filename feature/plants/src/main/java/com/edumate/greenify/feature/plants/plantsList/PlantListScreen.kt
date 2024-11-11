package com.edumate.greenify.feature.plants.plantsList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.edumate.greenify.core.ui.components.EmptyScreenPlaceholder
import com.edumate.greenify.core.ui.model.PlantUI
import com.edumate.greenify.core.ui.model.toPlantUI
import com.edumate.greenify.core.ui.theme.GreenifyTheme
import com.edumate.greenify.feature.plants.plantsList.components.PaginatedPlantsList
import com.edumate.greenify.feature.plants.plantsList.components.PlantListTopBar
import com.edumate.greenify.feature.plants.plantsList.components.previewPlant
import com.edumate.greenify.feature.plants.plantsList.core.PlantScreenState
import com.example.greenify.feature.plants.R
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantListScreen(
    countries: List<String>,
    screenState: PlantScreenState,
    onCountryFilterSelected: (index: Int) -> Unit,
    loadMore: () -> Unit,
    onPlantSelected: (PlantUI) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val topBarState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    // Check if the app bar is collapsing to hide the countries list
    val isAppBarCollapsed by remember {
        derivedStateOf {
            topAppBarState.collapsedFraction > .7f
        }
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Column(
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            stringResource(R.string.feature_plants_home),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        if (isAppBarCollapsed.not()) {
                            Spacer(modifier = Modifier.height(8.dp))
                            PlantListTopBar(
                                countries = countries,
                                selectedCountryIndex = screenState.countryIndex,
                                onCountrySelected = { index ->
                                    coroutineScope.launch {
                                        listState.scrollToItem(0)
                                    }
                                    onCountryFilterSelected(index)
                                },
                                listState = topBarState
                            )
                        }
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                coroutineScope.launch {
                    listState.animateScrollToItem(0)
                }
            }) {
                Icon(Icons.Filled.KeyboardArrowUp, contentDescription = null)
            }
        }
    ) { padding ->
        if (screenState.plants.isEmpty()) {
            if (screenState.isLoading) {
                Box(
                    modifier = modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    EmptyScreenPlaceholder(
                        placeholderImage = R.drawable.sunflower,
                        placeholderText = stringResource(R.string.feature_plants_no_plants_to_display)
                    )
                }
            }
        } else {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = padding.calculateTopPadding(), bottom = padding.calculateBottomPadding()),
                contentAlignment = Alignment.Center
            ) {
                PaginatedPlantsList(screenState, listState, loadMore, onPlantSelected)
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PlantScreenPreview() {
    GreenifyTheme {
        PlantListScreen(
            countries = emptyList(),
            screenState = PlantScreenState(
                isLoading = false,
                plants = (1..100).map { previewPlant.copy(id = it).toPlantUI() }.toPersistentList()
            ),
            onCountryFilterSelected = {},
            loadMore = {},
            onPlantSelected = {},
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}
