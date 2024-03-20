package com.roliveira.showtracker

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.roliveira.showtracker.screens.Home
import com.roliveira.showtracker.screens.Search
import com.roliveira.showtracker.screens.Show
import com.roliveira.showtracker.screens.Watchlist

@Composable
fun Navigation(viewModel: MainViewModel, navController: NavController, pd: PaddingValues) {

    NavHost(navController = navController as NavHostController, startDestination = Screen.Home.route, modifier = Modifier.padding(pd)) {
        composable(Screen.Home.route) {
            Home(viewModel, navController)
        }
        composable(Screen.WatchList.route) {
            Watchlist(viewModel, navController)
        }
        composable(Screen.Show.route + "/{id}") {
            backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")?.toInt()?: -1
                Show(viewModel, id)
        }
        composable(Screen.Search.route + "/{searchInput}") {
            backStackEntry ->
                val searchInput = backStackEntry.arguments?.getString("searchInput") ?: ""
                Search(viewModel, navController, searchInput)
        }
    }
}