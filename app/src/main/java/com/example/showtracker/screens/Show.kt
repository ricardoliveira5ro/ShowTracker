package com.example.showtracker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.showtracker.R
import com.example.showtracker.fonts.Typography
import com.example.showtracker.ui.theme.ShowTrackerTheme

@Composable
fun Show() {
    val screenHeight = with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.bojack_horseman),
                contentDescription = "Cover",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight / 3.5f)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Text(
                    text = "Bojack Horseman",
                    color = Color.White,
                    fontFamily = Typography.openSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.padding(vertical = 2.dp))

                Text(
                    text = "2019 - 2022",
                    color = colorResource(id = R.color.blue_font_1),
                    fontFamily = Typography.robotoFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }

            Column {
                val added = true
                val icon = if (added) R.drawable.added else R.drawable.add

                IconButton(onClick = {  }) {
                    Image(painter = painterResource(id = icon), contentDescription = "Add to Watchlist")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowPreview() {
    ShowTrackerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Show()
        }
    }
}