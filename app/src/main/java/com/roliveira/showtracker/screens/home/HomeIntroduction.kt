package com.roliveira.showtracker.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roliveira.showtracker.R
import com.roliveira.showtracker.fonts.Typography

@Composable
fun HomeIntroduction() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Hi there!", color = colorResource(id = R.color.blue_font_1), fontFamily = Typography.nexaFont, fontWeight = FontWeight.Normal, fontSize = 24.sp)
        Image(painter = painterResource(id = R.drawable.menu_pink), contentDescription = "Menu", modifier = Modifier.width(30.dp))
    }
}