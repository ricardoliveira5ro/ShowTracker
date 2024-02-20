package com.example.showtracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.showtracker.R
import com.example.showtracker.fonts.Typography
import com.example.showtracker.ui.theme.ShowTrackerTheme

@Composable
fun SearchBar(onSearchInputChanged: (String) -> Unit, onSearchSubmitted: () -> Unit, initialInput: String) {
    val inputValue = remember { mutableStateOf(TextFieldValue(initialInput)) }
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
                value = inputValue.value,
                onValueChange = {
                    inputValue.value = it
                    onSearchInputChanged(it.text)
                },
                textStyle = TextStyle(color = colorResource(id = R.color.blue_font_1), fontFamily = Typography.robotoFont, fontWeight = FontWeight.Normal, fontSize = 17.sp),
                decorationBox = { innerTextField ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        if (inputValue.value.text.isEmpty()) {
                            Text(text = "Search", color = colorResource(id = R.color.blue_font_1), fontFamily = Typography.robotoFont, fontWeight = FontWeight.Normal, fontSize = 16.sp)
                        }
                    }
                    innerTextField()
                },
                cursorBrush = SolidColor(colorResource(id = R.color.pink)),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { onSearchSubmitted() })
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    ShowTrackerTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            SearchBar(onSearchInputChanged = { }, onSearchSubmitted = { }, "")
        }
    }
}