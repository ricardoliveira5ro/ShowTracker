package com.example.showtracker.model

data class Season (
    val id: Int,
    val name: String,
    val season_number: String,
    val episode_count: String
)

data class SeasonsResponse(val seasons: List<Season>)