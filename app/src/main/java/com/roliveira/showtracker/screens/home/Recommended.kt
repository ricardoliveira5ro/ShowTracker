package com.roliveira.showtracker.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.roliveira.showtracker.R
import com.roliveira.showtracker.Screen
import com.roliveira.showtracker.model.TVShowShort
import com.roliveira.showtracker.utils.Utils

@Composable
fun Recommended(controller: NavController, items: List<TVShowShort>, screenWidth: Dp) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        for (item in items) {
            val painter = if (item.poster_path != null) rememberAsyncImagePainter(Utils.TMDB_IMAGES_BASE_URL + item.poster_path)
            else painterResource(id = R.drawable.no_image)
            ElevatedCard(
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                modifier = Modifier
                    .size(width = screenWidth / 2.4f, height = screenWidth / 1.6f)
                    .clickable { controller.navigate(Screen.Show.route + "/${item.id}") }
            ) {
                Image(
                    painter = painter,
                    contentDescription = item.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}