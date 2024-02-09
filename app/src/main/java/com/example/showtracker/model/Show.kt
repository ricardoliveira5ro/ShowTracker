package com.example.showtracker.model

import androidx.annotation.DrawableRes
import com.example.showtracker.R

data class Show (
    val id: Long = 0L,
    val title: String = "",
    @DrawableRes val imageResourceId: Int,
    val rating: Float,
    val time: Int,
    val seasons: List<Season>,
)

data class Season (
    val id: Long = 0L,
    val number: Int,
    val episodes: List<Episode>,
    val watched: Boolean = false,
)

data class Episode (
    val id: Long = 0L,
    val title: String = "",
    val number: Int,
    val watched: Boolean = false
)

object DummyShow {
    private val episodes = listOf(
        Episode(title = "The BoJack Horseman Story", number = 1, watched = true),
        Episode(title = "BoJack Hates the Troops", number = 2),
        Episode(title = "Prickly-Muffin", number = 3),
        Episode(title = "Zoës and Zeldas", number = 4),
        Episode(title = "Live Fast, Diane Nguyen", number = 5)
    )

    private val seasons = listOf(
        Season(episodes = episodes, number = 1)
    )

    val shows = listOf(
        Show(title = "The Witcher", imageResourceId = R.drawable.the_witcher, rating = 5f, time = 120, seasons = seasons),
        Show(title = "Final Space", imageResourceId = R.drawable.final_space, rating = 4f, time = 95, seasons = seasons),
        Show(title = "Rick & Morty", imageResourceId = R.drawable.rick_and_morty, rating = 3f, time = 110, seasons = seasons)
    )

    val recommended = listOf(
        Show(title = "Mr Robot", imageResourceId = R.drawable.mr_robot, rating = 5f, time = 120, seasons = seasons),
        Show(title = "Breaking Bad", imageResourceId = R.drawable.breaking_bad, rating = 2f, time = 95, seasons = seasons),
        Show(title = "Bojack Horseman", imageResourceId = R.drawable.bojack_horseman, rating = 4f, time = 110, seasons = seasons),
        Show(title = "The Blacklist", imageResourceId = R.drawable.blacklist, rating = 3f, time = 110, seasons = seasons)
    )

    val watchlistEmpty = listOf<Show>()

    val watchlist = listOf(
        Show(title = "Mr Robot", imageResourceId = R.drawable.mr_robot, rating = 3f, time = 120, seasons = seasons),
        Show(title = "Breaking Bad", imageResourceId = R.drawable.breaking_bad, rating = 4.8f, time = 95, seasons = seasons),
        Show(title = "Bojack Horseman", imageResourceId = R.drawable.bojack_horseman, rating = 3.2f, time = 110, seasons = seasons),
        Show(title = "The Blacklist", imageResourceId = R.drawable.blacklist, rating = 2.5f, time = 84, seasons = seasons)
    )

    var genres = listOf("Action", "Adventure", "Fantasy")
}