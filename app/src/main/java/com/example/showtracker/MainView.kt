package com.example.showtracker

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.showtracker.screens.Home


@Composable
fun MainView() {

    val viewModel: MainViewModel = viewModel()
    val currentScreen = remember {
        viewModel.currentScreen.value
    }

    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(

    ) {
        Navigation(navController = controller, viewModel = viewModel, pd = it)
    }
}

@Composable
fun Navigation(navController: NavController, viewModel: MainViewModel, pd:PaddingValues) {

    NavHost(navController = navController as NavHostController, startDestination = Screen.Home.route, modifier = Modifier.padding(pd)) {
        composable(Screen.Home.route) {
            Home()
        }
        composable(Screen.WatchList.route) {

        }
    }
}

/*@Composable
fun NavigationItem(
    selected: Boolean,
    item: Screen,
    onItemClicked: () -> Unit
) {

}*/