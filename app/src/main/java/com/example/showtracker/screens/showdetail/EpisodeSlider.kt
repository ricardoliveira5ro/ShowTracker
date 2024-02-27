package com.example.showtracker.screens.showdetail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.showtracker.model.TVShow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EpisodeSlider(show: TVShow, screenWidth: Dp) {
    val numberOfPages = show.episodes.size
    val nextEpisodeIndex = show.episodes.indexOfFirst { !it.isWatched }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 6.dp)
    ) {
        val state = rememberPagerState (
            initialPage = if (nextEpisodeIndex != -1) nextEpisodeIndex else 0,
            pageCount = { numberOfPages }
        )
        HorizontalPager(
            state = state,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = if (state.currentPage == 0) PaddingValues(end = 42.dp) else PaddingValues(horizontal = 32.dp)
        ) {
                page ->
            EpisodeItem(episode = show.episodes[page], imageEpisodeUrl = show.poster_path, width = screenWidth, isFirstEpisode = state.currentPage == 0)
        }
    }
}