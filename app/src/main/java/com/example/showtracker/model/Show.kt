package com.example.showtracker.model

import androidx.annotation.DrawableRes
import com.example.showtracker.R

data class Show (
    val id: Long = 0L,
    val title: String = "",
    @DrawableRes val imageResourceId: Int,
    val rating: Float,
    val time: Int,
    val seasons: Int,
    val episodes: List<Episode>,
)

data class Episode (
    val id: Long = 0L,
    val title: String = "",
    val number: Int,
    val season: Int,
    val watched: Boolean = false
)

object DummyShow {
    private val episodes = listOf(
        Episode(title = "The BoJack Horseman Story", number = 1, season = 1, watched = true),
        Episode(title = "BoJack Hates the Troops", number = 2, season = 1, watched = true),
        Episode(title = "Prickly-Muffin", number = 3, season = 1, watched = true),
        Episode(title = "Zoës and Zeldas", number = 4, season = 1),
        Episode(title = "Live Fast, Diane Nguyen", number = 5, season = 1),

        Episode(title = "The BoJack Horseman Story", number = 1, season = 2),
        Episode(title = "BoJack Hates the Troops", number = 2, season = 2),
        Episode(title = "Prickly-Muffin", number = 3, season = 2),
        Episode(title = "Zoës and Zeldas", number = 4, season = 2),
        Episode(title = "Live Fast, Diane Nguyen", number = 5, season = 2),
        Episode(title = "Zoës and Zeldas", number = 6, season = 2),
        Episode(title = "Live Fast, Diane Nguyen", number = 7, season = 2),

        Episode(title = "The BoJack Horseman Story", number = 1, season = 3),
        Episode(title = "BoJack Hates the Troops", number = 2, season = 3),
        Episode(title = "Prickly-Muffin", number = 3, season = 3),
        Episode(title = "Zoës and Zeldas", number = 4, season = 3),
        Episode(title = "Live Fast, Diane Nguyen", number = 5, season = 3),
        Episode(title = "The BoJack Horseman Story", number = 6, season = 3),
        Episode(title = "BoJack Hates the Troops", number = 7, season = 3),
        Episode(title = "Prickly-Muffin", number = 8, season = 3),
        Episode(title = "Zoës and Zeldas", number = 9, season = 3),
        Episode(title = "Live Fast, Diane Nguyen", number = 10, season = 3)
    )

    val shows = listOf(
        Show(title = "The Witcher", imageResourceId = R.drawable.the_witcher, rating = 5f, time = 120, seasons = 3, episodes = episodes),
        Show(title = "Final Space", imageResourceId = R.drawable.final_space, rating = 4f, time = 95, seasons = 1, episodes = episodes),
        Show(title = "Rick & Morty", imageResourceId = R.drawable.rick_and_morty, rating = 3f, time = 110, seasons = 1, episodes = episodes),
        Show(title = "Mr Robot", imageResourceId = R.drawable.mr_robot, rating = 5f, time = 120, seasons = 1, episodes = episodes),
        Show(title = "Breaking Bad", imageResourceId = R.drawable.breaking_bad, rating = 2f, time = 95, seasons = 1, episodes = episodes),
        Show(title = "Bojack Horseman", imageResourceId = R.drawable.bojack_horseman, rating = 4f, time = 110, seasons = 1, episodes = episodes),
        Show(title = "The Blacklist", imageResourceId = R.drawable.blacklist, rating = 3f, time = 110, seasons = 1, episodes = episodes)
    )

    val recommended = listOf(
        Show(title = "Mr Robot", imageResourceId = R.drawable.mr_robot, rating = 5f, time = 120, seasons = 1, episodes = episodes),
        Show(title = "Breaking Bad", imageResourceId = R.drawable.breaking_bad, rating = 2f, time = 95, seasons = 1, episodes = episodes),
        Show(title = "Bojack Horseman", imageResourceId = R.drawable.bojack_horseman, rating = 4f, time = 110, seasons = 1, episodes = episodes),
        Show(title = "The Blacklist", imageResourceId = R.drawable.blacklist, rating = 3f, time = 110, seasons = 1, episodes = episodes)
    )

    val testEmptyList = listOf<Show>()

    val watchlist = listOf(
        Show(title = "Mr Robot", imageResourceId = R.drawable.mr_robot, rating = 3f, time = 120, seasons = 1, episodes = episodes),
        Show(title = "Breaking Bad", imageResourceId = R.drawable.breaking_bad, rating = 4.8f, time = 95, seasons = 1, episodes = episodes),
        Show(title = "Bojack Horseman", imageResourceId = R.drawable.bojack_horseman, rating = 3.2f, time = 110, seasons = 1, episodes = episodes),
        Show(title = "The Blacklist", imageResourceId = R.drawable.blacklist, rating = 2.5f, time = 84, seasons = 1, episodes = episodes)
    )

    var genres = listOf("Action", "Adventure", "Fantasy")
}