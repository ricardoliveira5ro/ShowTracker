package com.example.showtracker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.showtracker.R
import com.example.showtracker.fonts.Typography.nexaFont
import com.example.showtracker.fonts.Typography.robotoFont
import com.example.showtracker.fonts.Typography.openSans
import com.example.showtracker.model.DummyShow
import com.example.showtracker.ui.theme.ShowTrackerTheme

@Composable
fun Home() {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp)) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Hi there!", color = colorResource(id = R.color.blue_font_1), fontFamily = nexaFont, fontWeight = FontWeight.Normal, fontSize = 24.sp)
                Image(painter = painterResource(id = R.drawable.menu_pink), contentDescription = "Menu", modifier = Modifier.width(30.dp))
            }


            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                val inputvalue = remember { mutableStateOf(TextFieldValue()) }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(colorResource(id = R.color.blue_boxes))
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Icon(painter = painterResource(id = R.drawable.search_icon), contentDescription = "Search", tint = colorResource(id = R.color.blue_font_1), modifier = Modifier.padding(end = 10.dp))
                        BasicTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = inputvalue.value,
                            onValueChange = { inputvalue.value = it },
                            textStyle = TextStyle(color = colorResource(id = R.color.blue_font_1), fontFamily = robotoFont, fontWeight = FontWeight.Normal, fontSize = 17.sp),
                            decorationBox = { innerTextField ->
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    if (inputvalue.value.text.isEmpty()) {
                                        Text(text = "Search", color = colorResource(id = R.color.blue_font_1), fontFamily = robotoFont, fontWeight = FontWeight.Normal, fontSize = 16.sp)
                                    }
                                }
                                innerTextField()
                            },
                            cursorBrush = SolidColor(colorResource(id = R.color.pink))
                        )
                    }
                }
            }


            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = "Currently watching", color = Color.White, fontFamily = openSans, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                LazyRow {
                    items (DummyShow.shows) {
                            show ->
                        ElevatedCard (
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            modifier = Modifier
                                .size(width = 240.dp, height = 130.dp)
                                .padding(end = 12.dp)
                        ) {
                            Box(
                                Modifier.fillMaxSize()
                            ) {
                                Image(painter = painterResource(id = show.imageResourceId), contentDescription = show.title, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                                Text(text = show.title, modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                                    .align(Alignment.BottomStart), color = Color.White, fontFamily = openSans, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.padding(12.dp))

            Text(text = "Recommended for you", modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), color = Color.White, fontFamily = openSans, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }

        items(DummyShow.recommended.chunked(2)) {
            recommended ->
                LazyRow(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    items(recommended) { recommended ->
                        ElevatedCard(
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            modifier = Modifier.size(155.dp, 260.dp)
                        ) {
                            Image(
                                painter = painterResource(id = recommended.imageResourceId),
                                contentDescription = recommended.title,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    ShowTrackerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Home()
        }
    }
}