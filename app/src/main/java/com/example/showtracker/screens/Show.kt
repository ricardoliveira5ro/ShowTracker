package com.example.showtracker.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.showtracker.MainViewModel
import com.example.showtracker.R
import com.example.showtracker.fonts.Typography
import com.example.showtracker.model.Episode
import com.example.showtracker.model.Genre
import com.example.showtracker.model.Season
import com.example.showtracker.model.TVShow
import com.example.showtracker.ui.theme.ShowTrackerTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Show(viewModel: MainViewModel, controller: NavController) {
    val screenHeight = with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp }
    val screenWidth = with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp }

    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val id = navBackStackEntry?.arguments?.getString("id")?.toInt()?: -1

    viewModel.fetchTVShow(id)
    val tvShowState by viewModel.tvShowState

    val baseImageUrl = "https://image.tmdb.org/t/p/original"

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            val painter = if (tvShowState.show.backdrop_path != null) rememberAsyncImagePainter(baseImageUrl + tvShowState.show.backdrop_path)
                            else painterResource(id = R.drawable.no_image_backdrop)
            Image(
                painter = painter,
                contentDescription = tvShowState.show.name + " cover",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight / 3.5f)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Text(
                    text = tvShowState.show.name,
                    color = Color.White,
                    fontFamily = Typography.openSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.padding(vertical = 2.dp))

                Text(
                    text = "${viewModel.getYear(tvShowState.show.first_air_date)} - ${viewModel.getYear(tvShowState.show.last_air_date)}",
                    color = colorResource(id = R.color.blue_font_1),
                    fontFamily = Typography.robotoFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }

            Column {
                val icon = if (tvShowState.show.watchlist) R.drawable.added else R.drawable.add

                IconButton(onClick = {  }) {
                    Image(painter = painterResource(id = icon), contentDescription = "Add/Remove to/from Watchlist")
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Star,
                    contentDescription = "Rating",
                    tint = Color.Yellow,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = viewModel.getRating(tvShowState.show.vote_average),
                    color = Color.White,
                    fontFamily = Typography.openSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }


            Row {
                LazyRow(
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    items(tvShowState.show.genres) {
                        genre ->
                            Box(
                                modifier = Modifier
                                    .padding(end = 5.dp)
                                    .border(
                                        BorderStroke(
                                            1.dp,
                                            colorResource(id = R.color.blue_font_1)
                                        ), RoundedCornerShape(6.dp)
                                    ),
                            ) {
                                Text(
                                    text = genre.name,
                                    color = Color.White,
                                    fontFamily = Typography.openSans,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(8.dp, 4.dp)
                                )
                            }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(
                text = tvShowState.show.overview,
                textAlign = TextAlign.Justify,
                color = Color.White,
                fontFamily = Typography.robotoFont,
                fontWeight = FontWeight.Light,
                fontSize = 13.sp,
                modifier = Modifier
                    .height(58.dp)
                    .verticalScroll(rememberScrollState())
            )
        }

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

        val numberOfPages = tvShowState.show.episodes.size
        val nextEpisodeIndex = tvShowState.show.episodes.indexOfFirst { !it.isWatched }

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
                    EpisodeItem(episode = tvShowState.show.episodes[page], imageEpisodeUrl = tvShowState.show.poster_path, width = screenWidth, isFirstEpisode = state.currentPage == 0)
            }
        }

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

        repeat(tvShowState.show.number_of_seasons) {
            seasonIndex ->
                val seasonEpisodes = tvShowState.show.episodes.filter { it.season_number == seasonIndex }
                val seasonEpisodesWatched = seasonEpisodes.count { it.isWatched }

                val progress = (seasonEpisodesWatched.toFloat() / seasonEpisodes.size.toFloat()) * 100

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp).padding(bottom = 18.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.play),
                            contentDescription = "Play Icon",
                            modifier = Modifier.size(32.dp).padding(top = 4.dp)
                        )
                    }

                    Column (
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .weight(1f)
                    ) {
                        Row(
                        ) {
                            val seasonName = if(seasonEpisodes.firstOrNull()?.season_number == 0) "Specials"
                                                else "Season ${seasonEpisodes.firstOrNull()?.season_number}"
                            Text(
                                text = seasonName,
                                color = Color.White,
                                fontFamily = Typography.robotoFont,
                                fontWeight = FontWeight.Light,
                                fontSize = 15.sp,
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row {
                            LinearProgressIndicator(
                                progress = progress / 100f,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(4.dp)
                                    .clip(RoundedCornerShape(16.dp)),
                                trackColor = colorResource(id = R.color.blue_boxes),
                                color = colorResource(id = R.color.blue_indicator),
                                strokeCap = StrokeCap.Round
                            )
                        }
                    }

                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "$seasonEpisodesWatched/${seasonEpisodes.size}",
                                color = Color.White,
                                fontFamily = Typography.robotoFont,
                                fontWeight = FontWeight.Medium,
                                fontSize = 15.sp,
                            )

                            Icon(imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight, contentDescription = "Right Icon", tint = Color.White)
                        }
                    }
                }
        }
    }
}

@Composable
fun EpisodeItem(
    episode: Episode,
    imageEpisodeUrl: String?,
    width: Dp,
    isFirstEpisode: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(width / 5)
            .padding(
                start = if (isFirstEpisode) 0.dp else 6.dp,
                top = 0.dp,
                end = if (isFirstEpisode) 16.dp else 6.dp,
                bottom = 0.dp
            ),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = colorResource(id = R.color.blue_bottom_menu)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.width(80.dp)) {
                val baseImageUrl = "https://image.tmdb.org/t/p/original"
                val painter = if (imageEpisodeUrl != null) rememberAsyncImagePainter(baseImageUrl + imageEpisodeUrl)
                                else painterResource(id = R.drawable.no_image)
                Image(
                    painter = painter,
                    contentDescription = "Episode cover",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "S${episode.season_number.toString().padStart(2, '0')} | E${episode.episode_number.toString().padStart(2, '0')}",
                        color = Color.White,
                        fontFamily = Typography.openSans,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )

                    val icon = if (episode.isWatched) R.drawable.added else R.drawable.add

                    IconButton(onClick = {  }, modifier = Modifier.size(24.dp)) {
                        Image(
                            painter = painterResource(id = icon),
                            contentDescription = "Watched",
                        )
                    }
                }

                Text(
                    text = episode.name,
                    color = Color.White,
                    fontFamily = Typography.robotoFont,
                    fontWeight = FontWeight.Light,
                    fontSize = 10.sp
                )
            }
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
            viewModel.setMockTVShow(
                TVShow(
                    0,
                    "TV Show",
                    "Temporal Nexus follows Dr. Emily Hartley, a physicist entangled in a conspiracy surrounding time travel. As she uncovers a hidden society manipulating the temporal streams, Emily must navigate moral dilemmas while racing to prevent a catastrophic rupture in the fabric of time itself.",
                    70,
                    4,
                    "2014-01-01",
                    "2020-01-01",
                    null,
                    null,
                    5f,
                    10,
                    listOf(Genre(1, "Drama"), Genre(2, "Comedy")),
                    listOf(Season(1, 1, 4), Season(2, 2, 3), Season(3, 3, 3), Season(0, 0, 1)),
                    listOf(
                        Episode(1, "Episode 1", 1, 1, true), Episode(2, "Episode 2", 1, 2), Episode(3, "Episode 3", 1, 3), Episode(4, "Episode 4", 1, 4),
                        Episode(5, "Episode 1", 2, 1), Episode(6, "Episode 2", 2, 2), Episode(7, "Episode 3", 2, 3),
                        Episode(8, "Episode 1", 3, 1), Episode(9, "Episode 2", 3, 2), Episode(10, "Episode 3", 3, 3),
                        Episode(0, "Episode 0", 0, 1),
                        ),
                    true
                )
            )
            val controller = rememberNavController()
            Show(viewModel, controller)
        }
    }
}