package com.roliveira.showtracker

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.roliveira.showtracker.model.Episode
import com.roliveira.showtracker.model.Genre
import com.roliveira.showtracker.model.Season
import com.roliveira.showtracker.model.TVShow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Locale

class DataStoreHelper(private val application: Application) {
    private val Context.protoDataStore: DataStore<ProtoShowItems> by dataStore(
        fileName = "shows.pb",
        serializer = TVShowsSerializer
    )

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    suspend fun saveShows(shows: List<TVShow>) : Flow<Boolean> {
        return flow {
            application.protoDataStore.updateData { store ->
                val storeItem = shows.map { it.toProtoShowItem() }
                store.toBuilder()
                    .clearShows()
                    .addAllShows(storeItem)
                    .build()
            }
            emit(true)
        }.catch { error ->
            Log.e("DataStoreHelper", "Error saving list of shows to datastore: ´saveShows´: ${error.message}", error)
            emit(false)
        }
    }

    fun loadShows(): Flow<List<TVShow>> {
        return application.protoDataStore.data.map { store ->
            store.showsList.map { it.toTVShow() }
        }
    }

    private fun TVShow.toProtoShowItem(): ProtoShowItem {
        return ProtoShowItem.newBuilder()
            .setId(this.id)
            .setName(this.name)
            .setOverview(this.overview)
            .setNumberOfEpisodes(this.number_of_episodes)
            .setNumberOfSeasons(this.number_of_seasons)
            .setFirstAirDate(this.first_air_date)
            .setLastAirDate(this.last_air_date)
            .setPosterPath(this.poster_path)
            .setBackdropPath(this.backdrop_path)
            .setVoteAverage(this.vote_average)
            .setVoteCount(this.vote_count)
            .setWatchlist(this.watchlist)
            .addAllGenres(this.genres.map { it.toProtoGenreItem() })
            .addAllSeasons(this.seasons.map { it.toProtoSeasonItem() })
            .addAllEpisodes(this.episodes.map { it.toProtoEpisodeItem() })
            .setLastEpisodeWatchedDate(this.lastEpisodeWatchedDate?.let { dateFormat.format(it) } ?: "")
            .build()
    }

    private fun ProtoShowItem.toTVShow(): TVShow {
        return TVShow(
            id = this.id,
            name = this.name,
            overview = this.overview,
            number_of_episodes = this.numberOfEpisodes,
            number_of_seasons = this.numberOfSeasons,
            first_air_date = this.firstAirDate,
            last_air_date = this.lastAirDate,
            poster_path = this.posterPath,
            backdrop_path = this.backdropPath,
            vote_average = this.voteAverage,
            vote_count = this.voteCount,
            watchlist = this.watchlist,
            episodes = this.episodesList.map { it.toEpisode() },
            seasons = this.seasonsList.map { it.toSeason() },
            genres = this.genresList.map { it.toGenre() },
            lastEpisodeWatchedDate = if (this.lastEpisodeWatchedDate.isNotEmpty()) dateFormat.parse(this.lastEpisodeWatchedDate) else null
        )
    }

    private fun Genre.toProtoGenreItem(): ProtoGenreItem {
        return ProtoGenreItem.newBuilder()
            .setIdGenre(this.id)
            .setNameGenre(this.name)
            .build()
    }

    private fun ProtoGenreItem.toGenre(): Genre {
        return Genre(
            id = this.idGenre,
            name = this.nameGenre
        )
    }

    private fun Season.toProtoSeasonItem(): ProtoSeasonItem {
        return ProtoSeasonItem.newBuilder()
            .setIdSeason(this.id)
            .setSeasonNumber(this.season_number)
            .setEpisodeCount(this.episode_count)
            .build()
    }

    private fun ProtoSeasonItem.toSeason(): Season {
        return Season(
            id = this.idSeason,
            season_number = this.seasonNumber,
            episode_count = this.episodeCount
        )
    }

    private fun Episode.toProtoEpisodeItem(): ProtoEpisodeItem {
        return ProtoEpisodeItem.newBuilder()
            .setIdEpisode(this.id)
            .setNameEpisode(this.name)
            .setEpisodeSeasonNumber(this.season_number)
            .setEpisodeNumber(this.episode_number)
            .setIsWatched(this.isWatched)
            .build()
    }

    private fun ProtoEpisodeItem.toEpisode(): Episode {
        return Episode(
            id = this.idEpisode,
            name = this.nameEpisode,
            season_number = this.episodeSeasonNumber,
            episode_number = this.episodeNumber,
            isWatched = this.isWatched
        )
    }
}