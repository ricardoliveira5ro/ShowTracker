package com.roliveira.showtracker

import com.roliveira.showtracker.model.EpisodesResponse
import com.roliveira.showtracker.model.TVShow
import com.roliveira.showtracker.model.TVShowsShortResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private val retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService = retrofit.create(APIService::class.java)

object Keys {
    init {
        System.loadLibrary("native-lib")
    }

    external fun encryptKey() : String
}

interface APIService {

    @GET("tv/top_rated")
    suspend fun getTopRatedTVShows(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = Keys.encryptKey()
    ):TVShowsShortResponse

    @GET("tv/{series_id}/recommendations")
    suspend fun getRecommendations(
        @Path("series_id") tvId: Int,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = Keys.encryptKey()
    ):TVShowsShortResponse

    @GET("search/tv")
    suspend fun getTVShowsBySearch(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = Keys.encryptKey()
    ):TVShowsShortResponse

    @GET("tv/{series_id}")
    suspend fun getTVShowById(
        @Path("series_id") tvId: Int,
        @Query("api_key") apiKey: String = Keys.encryptKey()
    ):TVShow

    @GET("tv/{series_id}/season/{season_number}")
    suspend fun getEpisodesBySeason(
        @Path("series_id") tvId: Int,
        @Path("season_number") seasonNumber: Int,
        @Query("api_key") apiKey: String = Keys.encryptKey()
    ):EpisodesResponse
}