package com.example.showtracker

import com.example.showtracker.model.DiscoverShowResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//private val retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
//    .addConverterFactory(GsonConverterFactory.create())
//    .build()
//
//val tvShowService = retrofit.create(APIService::class.java)

interface APIService {
    @GET("discover/tv")
    suspend fun getTVShows(@Query("api_key") apiKey: String = BuildConfig.tmdbKey):DiscoverShowResponse
}

object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: APIService by lazy {
        retrofit.create(APIService::class.java)
    }
}