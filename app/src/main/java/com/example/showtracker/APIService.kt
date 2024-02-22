package com.example.showtracker

import com.example.showtracker.model.DiscoverShowResponse
import com.example.showtracker.model.TVShowsShortResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService = retrofit.create(APIService::class.java)

interface APIService {
    @GET("discover/tv")
    suspend fun getTVShows(@Query("api_key") apiKey: String = BuildConfig.tmdbKey):DiscoverShowResponse

    @GET("tv/top_rated")
    suspend fun getTopRatedTVShows(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.tmdbKey
    ):TVShowsShortResponse
}