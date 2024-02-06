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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.showtracker.R
import com.example.showtracker.fonts.Typography
import com.example.showtracker.model.DummyShow
import com.example.showtracker.ui.theme.ShowTrackerTheme

@Composable
fun Watchlist() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
    )
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.tv), contentDescription = "TV icon", modifier = Modifier.size(28.dp))
            Text(
                text = "Watchlist",
                color = Color.White,
                fontFamily = Typography.openSans,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Icon(painter = painterResource(id = R.drawable.filter), contentDescription = "Filter icon", modifier = Modifier.size(24.dp), tint = colorResource(id = R.color.pink))
        }

        Spacer(modifier = Modifier.padding(vertical = 12.dp))

        LazyColumn(

        ) {
            items(DummyShow.watchlist) {
                show ->

                    BoxWithConstraints(
                        modifier = Modifier.padding(vertical = 12.dp)
                    ) {

                        val boxWidth = this.maxWidth
                        Card(
                            modifier = Modifier
                                .size(width = boxWidth / 3.5f, height = 140.dp)
                                .zIndex(2f),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Image(painter = painterResource(id = show.imageResourceId), contentDescription = show.title, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
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
                                            Text(text = show.title, color = Color.White, fontFamily = Typography.robotoFont, fontWeight = FontWeight.Medium, fontSize = 18.sp)
                                        }

                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(0.dp, 6.dp, 16.dp, 6.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            RatingBar(rating = show.rating)
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Image(painter = painterResource(id = R.drawable.timer), contentDescription = "Time", modifier = Modifier.size(width = 18.dp, height = 18.dp).padding(end = 4.dp))
                                                Text(text = "${show.time}m", color = Color.White, fontFamily = Typography.robotoFont, fontWeight = FontWeight.Light, fontSize = 14.sp)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
            }
        }
    }
}

@Composable
fun RatingBar(maxStars: Int = 5, rating: Float) {
    val density = LocalDensity.current.density

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxStars) {
            val isSelected = i <= rating
            Icon(
                imageVector = if (isSelected) Icons.Filled.Star
                                else Icons.Default.Star,
                contentDescription = null,
                tint = if (isSelected) colorResource(id = R.color.pink)
                        else Color(0x20FFFFFF),
                modifier = Modifier
                    .width((6f * density).dp)
                    .height((6f * density).dp)
            )

            if (i < maxStars) {
                Spacer(modifier = Modifier.width((0.5f * density).dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WatchlistPreview() {
    ShowTrackerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Watchlist()
        }
    }
}