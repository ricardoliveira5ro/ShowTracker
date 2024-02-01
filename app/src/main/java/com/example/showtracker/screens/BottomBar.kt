package com.example.showtracker.screens

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.showtracker.screens

@Composable
fun BottomBar(currentRoute: String?, controller: NavController) {
    BottomNavigation(Modifier.wrapContentSize()) {
        screens.forEach {
            item ->
                BottomNavigationItem(
                    selected = currentRoute == item.route,
                    onClick = { controller.navigate(item.route) },
                    icon = {
                        Icon(painter = painterResource(id = item.icon), contentDescription = item.title, modifier = Modifier.size(32.dp))
                    },
                    label = { Text(text = item.title) }
                )
        }
    }
}

