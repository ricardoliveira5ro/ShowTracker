package com.example.showtracker.utils

import com.example.showtracker.model.Episode
import com.example.showtracker.model.Genre
import com.example.showtracker.model.Season
import com.example.showtracker.model.TVShow
import com.example.showtracker.model.TVShowShort

object Utils {
    fun getYear(date: String): String {
        return date.split('-')[0]
    }

    fun getRating(rating: Float): String {
        return String.format("%.1f", (rating / 2))
    }

    const val TMDB_IMAGES_BASE_URL = "https://image.tmdb.org/t/p/original"

    val defaultTVShow = TVShow(-1, "", "", -1, -1, "", "", null, null, -1f, -1, emptyList(), emptyList(), emptyList())

    val mockTVShowsListPreview = listOf(
        TVShowShort(1, "TV Show 1", null, null, 5f, 3),
        TVShowShort(2, "TV Show 2", null, null, 5f, 3),
        TVShowShort(3, "TV Show 3", null, null, 5f, 3),
        TVShowShort(4, "TV Show 4", null, null, 5f, 3)
    )

    val mockTVShowPreview = TVShow(
        id =0,
        name ="TV Show",
        overview = "Temporal Nexus follows Dr. Emily Hartley, a physicist entangled in a conspiracy surrounding time travel. As she uncovers a hidden society manipulating the temporal streams, Emily must navigate moral dilemmas while racing to prevent a catastrophic rupture in the fabric of time itself.",
        number_of_episodes = 11,
        number_of_seasons = 4,
        first_air_date = "2014-01-01",
        last_air_date = "2020-01-01",
        poster_path = null,
        backdrop_path = null,
        vote_average = 5f,
        vote_count = 10,
        genres = listOf(Genre(1, "Drama"), Genre(2, "Comedy")),
        seasons = listOf(
            Season(1, 1, 4),
            Season(2, 2, 3),
            Season(3, 3, 3),
            Season(0, 0, 1)
        ),
        episodes = listOf(
            Episode(1, "Episode 1", 1, 1, true), Episode(2, "Episode 2", 1, 2), Episode(3, "Episode 3", 1, 3), Episode(4, "Episode 4", 1, 4),
            Episode(5, "Episode 1", 2, 1), Episode(6, "Episode 2", 2, 2), Episode(7, "Episode 3", 2, 3),
            Episode(8, "Episode 1", 3, 1), Episode(9, "Episode 2", 3, 2), Episode(10, "Episode 3", 3, 3),
            Episode(0, "Episode 0", 0, 1),
        ),
        watchlist = true
    )
}