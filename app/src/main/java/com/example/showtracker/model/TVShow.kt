package com.example.showtracker.model

data class TVShow(
    val id: Int,
    val name: String,
    val overview: String,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val episode_run_time: Int,
    val first_air_date: String,
    val last_air_date: String,
    val poster_path: String,
    val backdrop_path: String
)

data class TVShowsResponse(val tvShows: List<TVShow>)