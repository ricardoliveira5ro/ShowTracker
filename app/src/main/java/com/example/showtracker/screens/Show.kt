package com.example.showtracker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.showtracker.R
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
                modifier = Modifier.fillMaxWidth().height(screenHeight / 3.5f)
            )
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