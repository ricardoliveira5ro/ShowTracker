package com.example.showtracker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.showtracker.MainViewModel
import com.example.showtracker.R
import com.example.showtracker.Screen
import com.example.showtracker.fonts.Typography.nexaFont
import com.example.showtracker.fonts.Typography.robotoFont
import com.example.showtracker.fonts.Typography.openSans
import com.example.showtracker.model.DummyShow
import com.example.showtracker.model.TVShowShort
import com.example.showtracker.ui.theme.ShowTrackerTheme

@Composable
fun Home(viewModel: MainViewModel, controller: NavController) {
    val screenWidth = with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp }
    var searchInput by remember { mutableStateOf("") }

    val tvShowListState by viewModel.tvShowListState

    val baseImageUrl = "https://image.tmdb.org/t/p/original"

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 12.dp)) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Hi there!", color = colorResource(id = R.color.blue_font_1), fontFamily = nexaFont, fontWeight = FontWeight.Normal, fontSize = 24.sp)
                Image(painter = painterResource(id = R.drawable.menu_pink), contentDescription = "Menu", modifier = Modifier.width(30.dp))
            }


            Row (
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


            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = "Currently watching", color = Color.White, fontFamily = openSans, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                val showList = DummyShow.shows //DummyShow.testEmptyList
                if (showList.isEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.nothing),
                            contentDescription = "Nothing to show",
                            modifier = Modifier.size(65.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Nothing to show yet",
                            color = colorResource(id = R.color.blue_font_1),
                            fontFamily = robotoFont,
                            fontWeight = FontWeight.Medium,
                            fontSize = 13.sp
                        )
                    }
                }
                else {
                    LazyRow {
                        items(showList) {
                            show ->
                                ElevatedCard(
                                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                    modifier = Modifier
                                        .size(width = screenWidth / 1.6f, height = screenWidth / 3f)
                                        .padding(end = 12.dp)
                                        //.clickable { controller.navigate(Screen.Show.route) }
                                ) {
                                    Box(
                                        Modifier.fillMaxSize()
                                    ) {
                                        Image(
                                            painter = painterResource(id = show.imageResourceId),
                                            contentDescription = show.title,
                                            modifier = Modifier.fillMaxSize(),
                                            contentScale = ContentScale.Crop
                                        )
                                        Text(
                                            text = show.title,
                                            modifier = Modifier
                                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                                .align(Alignment.BottomStart),
                                            color = Color.White,
                                            fontFamily = openSans,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp
                                        )
                                    }
                                }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.padding(12.dp))

            Text(text = "Recommended for you", modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), color = Color.White, fontFamily = openSans, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }

        items(tvShowListState.list.chunked(2)) {
            recommended ->
                LazyRow(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    items(recommended) { recommended ->
                        val painter = if (recommended.poster_path != null) rememberAsyncImagePainter(baseImageUrl + recommended.poster_path)
                                        else painterResource(id = R.drawable.no_image)
                        ElevatedCard(
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            modifier = Modifier
                                .size(width = screenWidth / 2.5f, height = screenWidth / 1.6f)
                                .clickable { controller.navigate(Screen.Show.route + "/${recommended.id}") }
                        ) {
                            Image(
                                painter = painter,
                                contentDescription = recommended.name,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
        }
    }
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
            viewModel.setMockTVShowLists(
                listOf(
                    TVShowShort(1, "TV Show 1", null, null, 5f, 3),
                    TVShowShort(2, "TV Show 2", null, null, 5f, 3),
                    TVShowShort(3, "TV Show 3", null, null, 5f, 3),
                    TVShowShort(4, "TV Show 4", null, null, 5f, 3)
                )
            )
            val controller = rememberNavController()
            Home(viewModel, controller)
        }
    }
}