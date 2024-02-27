package com.example.showtracker.screens.showdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.showtracker.R
import com.example.showtracker.fonts.Typography
import com.example.showtracker.model.TVShow

@Composable
fun SeasonsList(show: TVShow, seasonNumber: Int) {
    val seasonEpisodes = show.episodes.filter { it.season_number == seasonNumber }
    val seasonEpisodesWatched = seasonEpisodes.count { it.isWatched }

    val progress = (seasonEpisodesWatched.toFloat() / seasonEpisodes.size.toFloat()) * 100

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(bottom = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.play),
                contentDescription = "Play Icon",
                modifier = Modifier
                    .size(32.dp)
                    .padding(top = 4.dp)
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