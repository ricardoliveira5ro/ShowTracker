package com.roliveira.showtracker.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.roliveira.showtracker.MainViewModel
import com.roliveira.showtracker.fonts.Typography
import com.roliveira.showtracker.screens.showdetail.BackdropImage
import com.roliveira.showtracker.screens.showdetail.EpisodeSlider
import com.roliveira.showtracker.screens.showdetail.Overview
import com.roliveira.showtracker.screens.showdetail.Rating_Genres
import com.roliveira.showtracker.screens.showdetail.SeasonsList
import com.roliveira.showtracker.screens.showdetail.TitleSection
import com.roliveira.showtracker.ui.theme.ShowTrackerTheme

@Composable
fun Show(viewModel: MainViewModel, id: Int) {
    val screenHeight = with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp.coerceAtLeast(0.dp) }
    val screenWidth = with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp.coerceAtLeast(0.dp) }

    if (viewModel.isInternetAvailable(LocalContext.current)) {
        var show = viewModel.loadTVShowById(id)

        // Set default show while fetching still loading (avoid previous load)
        if (show.id != id) show = MainViewModel.TVShowState().show

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            item {
                BackdropImage(show = show, screenHeight = screenHeight)
            }

            item {
                TitleSection(viewModel = viewModel, show = show)
            }

            item {
                Rating_Genres(show = show)
            }


            item {
                Overview(show = show)
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "Your next episode",
                        color = Color.White,
                        fontFamily = Typography.openSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }


            item {
                EpisodeSlider(viewModel = viewModel, show = show, screenWidth = screenWidth)
            }


            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                ) {
                    Text(
                        text = "Seasons",
                        color = Color.White,
                        fontFamily = Typography.openSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }

            items(show.seasons) { season ->
                SeasonsList(show = show, seasonNumber = season.season_number)
            }
        }
        
    } else {
        NoInternetAvailable(screenWidth = screenWidth)
    }
}

@Preview(showBackground = true)
@Composable
fun ShowPreview() {
    ShowTrackerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val viewModel: MainViewModel = viewModel()
            Show(viewModel, 0)
        }
    }
}