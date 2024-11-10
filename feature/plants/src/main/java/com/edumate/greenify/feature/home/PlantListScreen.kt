package com.edumate.greenify.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.edumate.greenify.core.ui.theme.GreenifyTheme
import com.edumate.greenify.core.ui.toPlantUI
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@Composable
fun PlantListScreen(
    countries: List<String>,
    screenState: PlantScreenState,
    onCountryFilterSelected: (index: Int) -> Unit,
    loadMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        topBar = {
            PlantListTopBar(countries, screenState.countryIndex, { index ->
                coroutineScope.launch {
                    listState.scrollToItem(0)
                }
                onCountryFilterSelected(index)

            })
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
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(padding),
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
                PaginatedPlantsList(loadMore, screenState, listState, modifier)
            }
        }
    }
}

@Composable
private fun PaginatedPlantsList(
    loadMore: () -> Unit,
    screenState: PlantScreenState,
    listState: LazyListState,
    modifier: Modifier,
) {
    val buffer = 5

    val shouldLoadMore = remember {
        derivedStateOf {
            val totalItemsCount = listState.layoutInfo.totalItemsCount
            val lastVisibleItemIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisibleItemIndex >= (totalItemsCount - buffer)
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { shouldLoadMore.value }
            .distinctUntilChanged()
            .filter { it }
            .collect {
                loadMore()
            }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),
        state = listState
    ) {
        items(items = screenState.plants, key = { it.id }) { plant ->
            PlantListItem(
                plant,
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (screenState.isLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
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
            countries = emptyList(),
            screenState = PlantScreenState(
                isLoading = false,
                plants = (1..100).map { previewPlant.copy(id = it).toPlantUI() }.toPersistentList()
            ),
            onCountryFilterSelected = {},
            loadMore = {},
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}
