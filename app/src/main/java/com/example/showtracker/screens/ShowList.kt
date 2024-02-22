package com.example.showtracker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.example.showtracker.R
import com.example.showtracker.fonts.Typography
import com.example.showtracker.model.TVShowShort
import com.example.showtracker.screens.stars.FractionalRatingBar
import com.example.showtracker.ui.theme.ShowTrackerTheme

@Composable
fun ShowList(showList: List<TVShowShort>) {
    if (showList.isEmpty()) {
        NothingToShowItem()
    }
    else {
        LazyColumn {
            items(showList) {
                show ->
                    ShowListItem(show = show)
            }
        }
    }
}

@Composable
fun ShowListItem(show: TVShowShort) {
    BoxWithConstraints(
        modifier = Modifier.padding(vertical = 12.dp)
    ) {

        val boxWidth = this.maxWidth
        val baseImageUrl = "https://image.tmdb.org/t/p/original"
        val painter = if (show.poster_path != null) rememberAsyncImagePainter(baseImageUrl + show.poster_path)
        else painterResource(id = R.drawable.no_image)

        Card(
            modifier = Modifier
                .size(width = boxWidth / 3.5f, height = 140.dp)
                .zIndex(2f),
            shape = RoundedCornerShape(6.dp)
        ) {
            Image(painter = painter, contentDescription = show.name, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        }
        Column() {
            Spacer(modifier = Modifier
                .height(20.dp)
                .width(10.dp))
            Row( ) {
                Spacer(modifier = Modifier.width(20.dp))
                Card(
                    modifier = Modifier.size(width = boxWidth, height = 130.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.blue_boxes))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 95.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(text = show.name, color = Color.White, fontFamily = Typography.robotoFont, fontWeight = FontWeight.Medium, fontSize = 18.sp)
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 6.dp, 16.dp, 6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row {
                                FractionalRatingBar(rating = show.vote_average / 2)
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = "(${show.vote_count})", color = Color.White, fontFamily = Typography.robotoFont, fontWeight = FontWeight.Light, fontSize = 14.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NothingToShowItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.nothing), contentDescription = "Nothing here", modifier = Modifier.size(130.dp))
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Nothing to show yet",
            color = colorResource(id = R.color.blue_font_1),
            fontFamily = Typography.robotoFont,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Explore more and find your next favorite show!",
            color = Color.White,
            fontFamily = Typography.robotoFont,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShowListPreview() {
    val showList = listOf(
        TVShowShort(1, "Bojack", "", "", 5f, 3),
        TVShowShort(2, "Bojack", "", "", 5f, 3)
    )
    ShowTrackerTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            ShowList(showList)
        }
    }
}