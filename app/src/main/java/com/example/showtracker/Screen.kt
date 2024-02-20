package com.example.showtracker

import androidx.annotation.DrawableRes

sealed class Screen(val title: String, val route: String, @DrawableRes val icon: Int?) {
    object Home: Screen("Home","home", R.drawable.home)
    object WatchList: Screen("Watchlist","watchlist", R.drawable.watchlist)
    object Show: Screen("Show","show", null)
    object Search: Screen("Search","search", null)
}

val screens = listOf(
    Screen.Home,
    Screen.WatchList
)