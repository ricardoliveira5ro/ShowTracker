package com.roliveira.showtracker.screens

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.roliveira.showtracker.MainViewModel
import com.roliveira.showtracker.Screen
import com.roliveira.showtracker.fonts.Typography
import com.roliveira.showtracker.screens.home.CurrentlyWatching
import com.roliveira.showtracker.screens.home.HomeIntroduction
import com.roliveira.showtracker.screens.home.Recommended
import com.roliveira.showtracker.ui.theme.ShowTrackerTheme

@Composable
fun Home(viewModel: MainViewModel, controller: NavController) {
    val screenWidth = with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp }

    if (viewModel.isInternetAvailable(LocalContext.current)) {
        val listState = rememberLazyListState()

        var searchInput by remember { mutableStateOf("") }

        val loadedTVShows by viewModel.loadedTVShows.observeAsState(emptyList())
        val recommendations by viewModel.recommendations.observeAsState(emptyList())
        val topRated by viewModel.topRated.observeAsState(emptyList())

        val showList = remember(loadedTVShows) {
            loadedTVShows.sortedByDescending { it.lastEpisodeWatchedDate }.filter { show ->
                val hasWatchedEpisode = show.episodes.any { episode -> episode.isWatched }
                val allEpisodesWatched = show.episodes.all { episode -> episode.isWatched }
                hasWatchedEpisode && !allEpisodesWatched
            }
        }

        LaunchedEffect(Unit) {
            viewModel.loadTVShowsFromDataStore()
        }

        LaunchedEffect(loadedTVShows) {
            val id = showList.firstOrNull()?.id ?: -1

            Log.d("MainViewModel", "id -> $id")

            if (id != -1) viewModel.fetchTVShowRecommendationsList(id)
            else viewModel.fetchTVShowTopRated()
        }

        val reachedBottom: Boolean by remember { derivedStateOf { listState.reachedBottom() } }

        LaunchedEffect(reachedBottom) {
            if (reachedBottom) {
                val id = showList.firstOrNull()?.id ?: -1

                Log.d("MainViewModel", "id bottom -> $id")

                if (id != -1) viewModel.fetchTVShowRecommendationsList(id)
                else viewModel.fetchTVShowTopRated(false)
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            state = listState
        ) {

            item {
                HomeIntroduction()
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    SearchBar(
                        onSearchInputChanged = { input -> searchInput = input },
                        onSearchSubmitted = { controller.navigate(Screen.Search.route + "/$searchInput") },
                        searchInput
                    )
                }
            }

            item {
                CurrentlyWatching(
                    controller = controller,
                    showList = showList,
                    screenWidth = screenWidth
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Recommended for you",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    color = Color.White,
                    fontFamily = Typography.openSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            val finalList =
                if ((showList.firstOrNull()?.id ?: -1) != -1) recommendations else topRated
            items(finalList.chunked(2)) { items ->
                Recommended(controller = controller, items = items, screenWidth = screenWidth)
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

    } else {
        NoInternetAvailable(screenWidth = screenWidth)
    }
}

private fun LazyListState.reachedBottom(): Boolean {
    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull()
    return lastVisibleItem?.index != 0 && lastVisibleItem?.index == this.layoutInfo.totalItemsCount - 1
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    ShowTrackerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val viewModel: MainViewModel = viewModel()
            val controller = rememberNavController()
            Home(viewModel, controller)
        }
    }
}