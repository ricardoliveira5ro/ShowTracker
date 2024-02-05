package com.example.showtracker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
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
            .padding(16.dp),
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
                    Row(
                        modifier = Modifier.padding(bottom = 8.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Card(
                            modifier = Modifier
                                .size(width = 100.dp, height = 140.dp)
                                .padding(8.dp)
                                .zIndex(1f)
                        ) {
                            Image(painter = painterResource(id = show.imageResourceId), contentDescription = show.title, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                        }
                        Card(
                            modifier = Modifier
                                .size(width = 300.dp, height = 100.dp)
                                .offset(x = (-60).dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.blue_boxes))
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 12.dp)
                                ) {
                                    Text(text = show.title, color = Color.White, fontFamily = Typography.robotoFont, fontWeight = FontWeight.Medium, fontSize = 18.sp)
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 12.dp, vertical = 6.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = "3 estrelas", color = Color.White, fontFamily = Typography.robotoFont, fontWeight = FontWeight.Light, fontSize = 14.sp)
                                    Text(text = "120m", color = Color.White, fontFamily = Typography.robotoFont, fontWeight = FontWeight.Light, fontSize = 14.sp)
                                }
                            }
                        }
                    }
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