package com.example.showtracker.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.showtracker.MainViewModel
import com.example.showtracker.fonts.Typography
import com.example.showtracker.screens.showdetail.BackdropImage
import com.example.showtracker.screens.showdetail.EpisodeSlider
import com.example.showtracker.screens.showdetail.Overview
import com.example.showtracker.screens.showdetail.Rating_Genres
import com.example.showtracker.screens.showdetail.SeasonsList
import com.example.showtracker.screens.showdetail.TitleSection
import com.example.showtracker.ui.theme.ShowTrackerTheme
import com.example.showtracker.utils.Utils

@Composable
fun Show(viewModel: MainViewModel, controller: NavController) {
    val screenHeight = with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp }
    val screenWidth = with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp }

    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val id = navBackStackEntry?.arguments?.getString("id")?.toInt()?: -1

    val show = viewModel.loadTVShowById(id)

    //TODO: Bug fix --> when loaded from proto datastore id could be -1 / when loaded async by API could be -1 or previous show id clicked

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        item {
            BackdropImage(show = show, screenHeight = screenHeight)
        }

        item {
            TitleSection(viewModel = viewModel, show = show, id = id)
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
            EpisodeSlider(show = show, screenWidth = screenWidth)
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

        items(show.seasons) {
            season ->
                SeasonsList(show = show, seasonNumber = season.season_number)
        }
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
            viewModel.setMockTVShow(Utils.mockTVShowPreview)
            val controller = rememberNavController()
            Show(viewModel, controller)
        }
    }
}