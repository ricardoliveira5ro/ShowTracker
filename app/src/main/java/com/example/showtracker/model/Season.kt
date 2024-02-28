package com.example.showtracker.model

import kotlinx.serialization.Serializable

@Serializable
data class Season (
    val id: Int,
    val season_number: Int,
    val episode_count: Int
)