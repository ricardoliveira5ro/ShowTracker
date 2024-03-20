package com.roliveira.showtracker

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.roliveira.showtracker.screens.BottomBar
import com.roliveira.showtracker.ui.theme.ShowTrackerTheme

@Composable
fun MainView(viewModel: MainViewModel) {
    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val currentScreen = when (currentRoute) {
        Screen.Home.route -> Screen.Home
        Screen.WatchList.route -> Screen.WatchList
        Screen.Show.route -> Screen.Show
        else -> Screen.Home
    }

    Scaffold(
        bottomBar = {
            if (currentScreen is Screen.Home || currentScreen is Screen.WatchList || currentScreen is Screen.Search) BottomBar(currentRoute, controller)
        },
        contentWindowInsets = if (currentScreen is Screen.Show) WindowInsets.navigationBars else WindowInsets.systemBars
    ) {
        Navigation(viewModel = viewModel, navController = controller, pd = it)
    }
}

@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
    ShowTrackerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            //val viewModel = MainViewModel()
            //MainView(viewModel)
        }
    }
}