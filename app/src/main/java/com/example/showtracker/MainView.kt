package com.example.showtracker

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


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



/*@Composable
fun NavigationItem(
    selected: Boolean,
    item: Screen,
    onItemClicked: () -> Unit
) {

}*/