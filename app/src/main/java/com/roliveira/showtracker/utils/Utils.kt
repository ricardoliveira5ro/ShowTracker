package com.roliveira.showtracker.utils

object Utils {
    fun getYear(date: String): String {
        return date.split('-')[0]
    }

    fun getRating(rating: Float): String {
        return String.format("%.1f", (rating / 2))
    }

    const val TMDB_IMAGES_BASE_URL = "https://image.tmdb.org/t/p/original"
}