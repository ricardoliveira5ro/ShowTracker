package com.example.showtracker.model

data class Episode (
    val id: Int,
    val name: String,
    val season_number: Int,
    val episode_number: Int
)

data class EpisodesResponse (val episodes: List<Episode>)