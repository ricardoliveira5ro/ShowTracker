package com.example.showtracker.model

import com.example.showtracker.R

data class Show (
    val id: Long = 0L,
    val title: String = "",
    val imageResourceId: Int
)

object DummyShow {
    val shows = listOf(
        Show(title = "The Witcher", imageResourceId = R.drawable.the_witcher),
        Show(title = "Final Space", imageResourceId = R.drawable.final_space),
        Show(title = "Rick & Morty", imageResourceId = R.drawable.rick_and_morty)
    )

    val recommended = listOf(
        Show(title = "Mr Robot", imageResourceId = R.drawable.mr_robot),
        Show(title = "Breaking Bad", imageResourceId = R.drawable.breaking_bad),
        Show(title = "Bojack Horseman", imageResourceId = R.drawable.bojack_horseman),
        Show(title = "The Blacklist", imageResourceId = R.drawable.blacklist)
    )
}