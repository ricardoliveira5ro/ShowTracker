package com.roliveira.showtracker.screens.showdetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roliveira.showtracker.R
import com.roliveira.showtracker.fonts.Typography
import com.roliveira.showtracker.model.TVShow
import com.roliveira.showtracker.utils.Utils

@Composable
fun Rating_Genres(show: TVShow) {
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
                text = Utils.getRating(show.vote_average),
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
                items(show.genres) {
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
                                text = genre.name,
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
}