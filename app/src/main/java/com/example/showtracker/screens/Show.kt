package com.example.showtracker.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.showtracker.R
import com.example.showtracker.fonts.Typography
import com.example.showtracker.model.DummyShow
import com.example.showtracker.model.Episode
import com.example.showtracker.ui.theme.ShowTrackerTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Show() {
    val screenHeight = with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.bojack_horseman),
                contentDescription = "Cover",
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
                    text = "Bojack Horseman",
                    color = Color.White,
                    fontFamily = Typography.openSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.padding(vertical = 2.dp))

                Text(
                    text = "2019 - 2022",
                    color = colorResource(id = R.color.blue_font_1),
                    fontFamily = Typography.robotoFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }

            Column {
                val added = true
                val icon = if (added) R.drawable.added else R.drawable.add

                IconButton(onClick = {  }) {
                    Image(painter = painterResource(id = icon), contentDescription = "Add to Watchlist")
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
                    text = "4.6",
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
                    items(DummyShow.genres) {
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
                                    text = genre,
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
                text = "BoJack é um decadente cavalo que trabalha na TV. Uma estrela já esquecida de uma série da década de 1990 chamada Horsin' Around, ele disfarça a sua baixa auto-estima com uísque e relações fracassadas. Com a ajuda de Todd, o seu parceiro humano, e da ex-amante Princesa Caroline , ele quer deixar novamente a sua marca no mundo do entretenimento",
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

        val show = DummyShow.shows.first()
        val numberOfPages = show.episodes.size

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 6.dp)
        ) {
            val state = rememberPagerState { numberOfPages }
            HorizontalPager(
                state = state
            ) {
                page ->
                    EpisodeItem(episode = show.episodes[page])
            }
        }

    }
}

@Composable
fun EpisodeItem(
    episode: Episode
) {
    Row(
        modifier = Modifier
            .size(280.dp, 80.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(colorResource(id = R.color.blue_bottom_menu)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.width(80.dp)) {
            Image(
                painter = painterResource(id = R.drawable.bojack_horseman),
                contentDescription = "Episode",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp)
        ) {
            Text(
                text = "S${episode.season.toString().padStart(2, '0')} | E${episode.number.toString().padStart(2, '0')}",
                color = Color.White,
                fontFamily = Typography.openSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = episode.title,
                color = Color.White,
                fontFamily = Typography.robotoFont,
                fontWeight = FontWeight.Light,
                fontSize = 10.sp
            )
        }

        Column {
            val added = false
            val icon = if (added) R.drawable.added else R.drawable.add

            IconButton(onClick = {  }) {
                Image(painter = painterResource(id = icon), contentDescription = "Watched", modifier = Modifier.size(24.dp, 24.dp))
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
            Show()
        }
    }
}