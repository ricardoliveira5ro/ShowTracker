package com.example.showtracker.model

data class Episode (
    val id: Int,
    val name: String,
    val season_number: String,
    val episode_count: String
)

data class EpisodesResponse(val tvShows: List<Episode>)