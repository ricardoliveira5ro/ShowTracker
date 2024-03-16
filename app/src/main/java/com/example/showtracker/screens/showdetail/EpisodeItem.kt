package com.example.showtracker.screens.showdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.showtracker.MainViewModel
import com.example.showtracker.R
import com.example.showtracker.fonts.Typography
import com.example.showtracker.model.TVShow
import com.example.showtracker.utils.Utils
import java.util.Calendar

@Composable
fun EpisodeItem(
    viewModel: MainViewModel,
    show: TVShow,
    episodeIndex: Int,
    imageEpisodeUrl: String?,
    width: Dp,
    isFirstEpisode: Boolean,
    onNextEpisodeChange: (Int) -> Unit
) {
    val episode by remember { mutableStateOf(show.episodes[episodeIndex]) }

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
                val painter = if (imageEpisodeUrl != null) rememberAsyncImagePainter(Utils.TMDB_IMAGES_BASE_URL + imageEpisodeUrl)
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

                    var imageSource by remember { mutableStateOf(if (episode.isWatched) R.drawable.added else R.drawable.add) }

                    IconButton(
                        modifier = Modifier.size(24.dp),
                        onClick = {
                            episode.isWatched = !episode.isWatched
                            imageSource = if (episode.isWatched) R.drawable.added else R.drawable.add
                            show.lastEpisodeWatchedDate = Calendar.getInstance().time
                            viewModel.saveTVShowsToDataStore(show)
                            onNextEpisodeChange(show.episodes.indexOfFirst { !it.isWatched })
                        }
                    ) {
                        Image(
                            painter = painterResource(id = imageSource),
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