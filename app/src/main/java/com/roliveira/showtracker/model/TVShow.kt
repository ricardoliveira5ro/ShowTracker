package com.roliveira.showtracker.model

import java.util.Date

data class TVShow(
    val id: Int,
    val name: String,
    val overview: String,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val first_air_date: String,
    val last_air_date: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val vote_average: Float,
    val vote_count: Int,
    val genres: List<Genre>,
    val seasons: List<Season>,
    val episodes: List<Episode>,
    var watchlist: Boolean = false,
    var lastEpisodeWatchedDate: Date?
)

data class TVShowShort(
    val id: Int,
    val name: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val vote_average: Float,
    val vote_count: Int
)

data class TVShowsShortResponse(val results: List<TVShowShort>)