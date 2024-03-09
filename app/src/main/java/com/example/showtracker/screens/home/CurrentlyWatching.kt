package com.example.showtracker.screens.home

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.showtracker.R
import com.example.showtracker.Screen
import com.example.showtracker.fonts.Typography.openSans
import com.example.showtracker.fonts.Typography.robotoFont
import com.example.showtracker.model.TVShow
import com.example.showtracker.utils.Utils

@Composable
fun CurrentlyWatching(controller: NavController, showList: List<TVShow>, screenWidth: Dp) {
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
                items(showList.size) {index ->
                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        modifier = Modifier
                            .size(width = screenWidth / 1.6f, height = screenWidth / 3f)
                            .padding(end = 12.dp)
                            .clickable { controller.navigate(Screen.Show.route + "/${showList[index].id}") }
                    ) {
                        Box(
                            Modifier.fillMaxSize()
                        ) {
                            val painter = if (showList[index].backdrop_path != null) rememberAsyncImagePainter(Utils.TMDB_IMAGES_BASE_URL + showList[index].backdrop_path)
                            else painterResource(id = R.drawable.no_image)
                            Image(
                                painter = painter,
                                contentDescription = showList[index].name,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                text = showList[index].name,
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
}