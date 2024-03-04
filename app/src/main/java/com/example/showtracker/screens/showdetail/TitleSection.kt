package com.example.showtracker.screens.showdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.showtracker.MainViewModel
import com.example.showtracker.R
import com.example.showtracker.fonts.Typography
import com.example.showtracker.model.TVShow
import com.example.showtracker.utils.Utils

@Composable
fun TitleSection(viewModel: MainViewModel, show: TVShow) {
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
                text = show.name,
                color = Color.White,
                fontFamily = Typography.openSans,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.padding(vertical = 2.dp))

            Text(
                text = "${Utils.getYear(show.first_air_date)} - ${Utils.getYear(show.last_air_date)}",
                color = colorResource(id = R.color.blue_font_1),
                fontFamily = Typography.robotoFont,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }

        Column {
            if (show.id != -1) {
                var imageSource by remember { mutableStateOf(if (show.watchlist) R.drawable.added else R.drawable.add) }

                IconButton(
                    onClick = {
                        show.watchlist = !show.watchlist
                        imageSource = if (show.watchlist) R.drawable.added else R.drawable.add
                        viewModel.saveTVShowsToDataStore(show)
                    }
                ) {
                    Image(painter = painterResource(id = imageSource), contentDescription = "Add/Remove to/from Watchlist")
                }
            }
        }
    }
}