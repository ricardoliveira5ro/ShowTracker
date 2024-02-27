package com.example.showtracker.screens.showdetail

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.showtracker.fonts.Typography
import com.example.showtracker.model.TVShow

@Composable
fun Overview(show: TVShow) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Text(
            text = show.overview,
            textAlign = TextAlign.Justify,
            color = Color.White,
            fontFamily = Typography.robotoFont,
            fontWeight = FontWeight.Light,
            fontSize = 13.sp,
            modifier = Modifier
                .height(58.dp)
                .verticalScroll(rememberScrollState())
        )
    }
}