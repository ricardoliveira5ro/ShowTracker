package com.example.showtracker

import com.example.showtracker.model.TVShowsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val tvShowService = retrofit.create(APIService::class.java)

interface APIService {
    @GET("discover/tv")
    suspend fun getTVShows():TVShowsResponse
}