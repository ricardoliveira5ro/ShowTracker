package com.roliveira.showtracker.screens.showdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil.compose.rememberAsyncImagePainter
import com.roliveira.showtracker.R
import com.roliveira.showtracker.model.TVShow
import com.roliveira.showtracker.utils.Utils

@Composable
fun BackdropImage(show: TVShow, screenHeight: Dp) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        val painter = if (show.backdrop_path != null) rememberAsyncImagePainter(Utils.TMDB_IMAGES_BASE_URL + show.backdrop_path)
        else painterResource(id = R.drawable.no_image_backdrop)
        Image(
            painter = painter,
            contentDescription = show.name + " cover",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight / 3.5f)
        )
    }
}