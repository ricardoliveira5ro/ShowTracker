package com.example.showtracker

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.showtracker.model.TVShow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

private val Context.protoDataStore: DataStore<ProtoShowItems> by dataStore(
    fileName = "shows.pb",
    serializer = TVShowsSerializer
)

class DataStoreHelper(val context: Context) {

    suspend fun saveShows(shows: List<TVShow>) : Flow<Boolean> {
        return flow {
            context.protoDataStore.updateData { store ->
                val storeItem = shows.map { it.toProtoShowItem() }
                store.toBuilder()
                    .clearShows()
                    .addAllShows(storeItem)
                    .build()
            }
            emit(true)
        }.catch { error ->
            Log.e("DataStoreHelper", "Error saving show: ${error.message}", error)
            emit(false)
        }
    }

    fun loadShows(): Flow<List<TVShow>> {
        return context.protoDataStore.data.map { store ->
            store.showsList.map { it.toTVShow() }
        }
    }

    private fun TVShow.toProtoShowItem(): ProtoShowItem {
        return ProtoShowItem.newBuilder()
            .setId(this.id)
            .setName(this.name)
            .setOverview(this.overview)
            .setFirstAirDate(this.first_air_date)
            .setLastAirDate(this.last_air_date)
            .setPosterPath(this.poster_path)
            .setBackdropPath(this.backdrop_path)
            .setVoteAverage(this.vote_average)
            .setVoteCount(this.vote_count)
            .setWatchlist(this.watchlist)
            .build()
    }

    private fun ProtoShowItem.toTVShow(): TVShow {
        return TVShow(
            id = this.id,
            name = this.name,
            overview = this.overview,
            first_air_date = this.firstAirDate,
            last_air_date = this.lastAirDate,
            poster_path = this.posterPath,
            backdrop_path = this.backdropPath,
            vote_average = this.voteAverage,
            vote_count = this.voteCount,
            watchlist = this.watchlist,
            episodes = emptyList(),
            seasons = emptyList(),
            genres = emptyList(),
            number_of_episodes = 1,
            number_of_seasons = 1
        )
    }
}