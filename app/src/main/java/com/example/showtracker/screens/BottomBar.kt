package com.example.showtracker.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.showtracker.R
import com.example.showtracker.screens

@Composable
fun BottomBar(currentRoute: String?, controller: NavController) {
    BottomAppBar(
        modifier = Modifier.wrapContentSize().clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp)),
        backgroundColor = colorResource(id = R.color.blue_bottom_menu),
        contentColor = Color.White
    ) {
        screens.forEach {
            item ->
                val selected = currentRoute == item.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = { controller.navigate(item.route) },
                    icon = {
                        item.icon?.let { painterResource(id = it) }?.let {
                            Icon(
                                painter = it,
                                contentDescription = item.title,
                                modifier = Modifier.size(26.dp),
                                tint = if (selected) colorResource(id = R.color.pink) else colorResource(id = R.color.grey_button)
                            )
                        }
                    },
                    modifier = Modifier.padding(vertical = 4.dp)
                )
        }
    }
}