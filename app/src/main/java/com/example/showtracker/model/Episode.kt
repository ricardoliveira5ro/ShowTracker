package com.example.showtracker.model

import kotlinx.serialization.Serializable

@Serializable
data class Episode (
    val id: Int,
    val name: String,
    val season_number: Int,
    val episode_number: Int,
    val isWatched: Boolean = false
)

data class EpisodesResponse (val episodes: List<Episode>)