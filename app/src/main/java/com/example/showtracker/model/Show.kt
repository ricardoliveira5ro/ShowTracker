package com.example.showtracker.model

import androidx.annotation.DrawableRes
import com.example.showtracker.R

data class Show (
    val id: Long = 0L,
    val title: String = "",
    @DrawableRes val imageResourceId: Int,
    val rating: Float,
    val time: Int
)

object DummyShow {
    val shows = listOf(
        Show(title = "The Witcher", imageResourceId = R.drawable.the_witcher, rating = 5f, time = 120),
        Show(title = "Final Space", imageResourceId = R.drawable.final_space, rating = 4f, time = 95),
        Show(title = "Rick & Morty", imageResourceId = R.drawable.rick_and_morty, rating = 3f, time = 110)
    )

    val recommended = listOf(
        Show(title = "Mr Robot", imageResourceId = R.drawable.mr_robot, rating = 5f, time = 120),
        Show(title = "Breaking Bad", imageResourceId = R.drawable.breaking_bad, rating = 2f, time = 95),
        Show(title = "Bojack Horseman", imageResourceId = R.drawable.bojack_horseman, rating = 4f, time = 110),
        Show(title = "The Blacklist", imageResourceId = R.drawable.blacklist, rating = 3f, time = 110)
    )

    val watchlistEmpty = listOf<Show>()

    val watchlist = listOf(
        Show(title = "Mr Robot", imageResourceId = R.drawable.mr_robot, rating = 2f, time = 120),
        Show(title = "Breaking Bad", imageResourceId = R.drawable.breaking_bad, rating = 5f, time = 95),
        Show(title = "Bojack Horseman", imageResourceId = R.drawable.bojack_horseman, rating = 3f, time = 110),
        Show(title = "The Blacklist", imageResourceId = R.drawable.blacklist, rating = 2.5f, time = 84)
    )
}