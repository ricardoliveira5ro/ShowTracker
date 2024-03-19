package com.roliveira.showtracker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roliveira.showtracker.R
import com.roliveira.showtracker.fonts.Typography
import com.roliveira.showtracker.ui.theme.ShowTrackerTheme

@Composable
fun NoInternetAvailable(screenWidth: Dp) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_internet),
            contentDescription = "No internet logo",
            modifier = Modifier.size(screenWidth / 4.5f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Ooops!",
            color = Color.White,
            fontFamily = Typography.openSans,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )
        Text(
            text = "No internet connection",
            color = colorResource(id = R.color.blue_font_1),
            fontFamily = Typography.robotoFont,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NoInternetAvailablePreview() {
    ShowTrackerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NoInternetAvailable(500.dp)
        }
    }
}