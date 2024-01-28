package com.example.showtracker

sealed class Screen(val title: String, val route: String/*, @DrawableRes val icon: Int*/) {
    object Home: Screen("Home","home")
    object WatchList: Screen("Watchlist","watchlist")
}