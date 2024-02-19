package com.example.showtracker.screens

import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.showtracker.R
import com.example.showtracker.fonts.Typography
import com.example.showtracker.model.DummyShow
import com.example.showtracker.ui.theme.ShowTrackerTheme
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun ShowList() {
    val showList = DummyShow.watchlist //DummyShow.testEmptyList
    if (showList.isEmpty()) {
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
    else {
        LazyColumn {
            items(showList) {
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
                                        FractionalRatingBar(rating = show.rating)
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Image(painter = painterResource(id = R.drawable.timer), contentDescription = "Time", modifier = Modifier
                                                .size(width = 18.dp, height = 18.dp)
                                                .padding(end = 4.dp))
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
fun FractionalRatingBar(
    rating: Float,
    maxRating: Int = 5,
    filledColor: Color = colorResource(id = R.color.pink),
    emptyColor: Color = Color(0x20FFFFFF)
) {
    val density = LocalDensity.current.density
    val starSize = (6f * density).dp

    Row(
        horizontalArrangement = Arrangement.Start,
    ) {
        val fullStars = floor(rating).toInt()
        val partialStarFill = rating - fullStars
        val emptyStars = maxRating - ceil(rating).toInt()

        repeat(fullStars) {
            Icon(Icons.Filled.Star, contentDescription = null, tint = filledColor, modifier = Modifier.size(starSize))
        }

        if (partialStarFill > 0) {
            PartialStar(
                starSize = starSize - 3.dp,
                filledColor = filledColor,
                emptyColor = emptyColor,
                fillPercentage = partialStarFill
            )
        }

        repeat(emptyStars) {
            Icon(Icons.Filled.Star, contentDescription = null, tint = emptyColor, modifier = Modifier.size(starSize))
        }
    }
}

@Composable
private fun PartialStar(
    starSize: Dp,
    filledColor: Color,
    emptyColor: Color,
    fillPercentage: Float
) {
    Canvas(modifier = Modifier.size(starSize)) {
        val starPath = createStarPath(size.width, size.height)

        translate(0f, 1.dp.toPx()) {
            clipRect(
                left = 0f,
                top = 0f,
                right = size.width * fillPercentage,
                bottom = size.height
            ) {
                drawPath(starPath, color = filledColor)
            }
            clipRect(
                left = size.width * fillPercentage,
                top = 0f,
                right = size.width,
                bottom = size.height
            ) {
                drawPath(starPath, color = emptyColor)
            }
        }
    }
}


private fun createStarPath(width: Float, height: Float): Path {
    val path = Path()
    path.moveTo(0.5f * width, 0.0f * height)
    path.lineTo(0.628f * width, 0.377f * height)
    path.lineTo(1.0f * width, 0.377f * height)
    path.lineTo(0.688f * width, 0.613f * height)
    path.lineTo(0.809f * width, 1.0f * height)
    path.lineTo(0.5f * width, 0.753f * height)
    path.lineTo(0.191f * width, 1.0f * height)
    path.lineTo(0.312f * width, 0.613f * height)
    path.lineTo(0.0f * width, 0.377f * height)
    path.lineTo(0.372f * width, 0.377f * height)
    path.close()
    return path
}

@Preview(showBackground = true)
@Composable
fun ShowListPreview() {
    ShowTrackerTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            ShowList()
        }
    }
}