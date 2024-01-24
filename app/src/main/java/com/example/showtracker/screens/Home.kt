package com.example.showtracker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.showtracker.R
import com.example.showtracker.ui.theme.ShowTrackerTheme

@Composable
fun Home() {
    val robotoFont = FontFamily(
        Font(R.font.roboto_regular, FontWeight.Normal),
        Font(R.font.roboto_light, FontWeight.Light),
        Font(R.font.roboto_medium, FontWeight.Medium),
        Font(R.font.roboto_bold, FontWeight.Bold),
        Font(R.font.roboto_black, FontWeight.Black)
    )

    val nexaFont = FontFamily(
        Font(R.font.nexa_extralight, FontWeight.ExtraLight),
        Font(R.font.nexa_heavy, FontWeight.Normal)
    )


    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                    .clip(RoundedCornerShape(16.dp))
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
                        cursorBrush = SolidColor(colorResource(id = R.color.blue_background))
                    )
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