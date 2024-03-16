package com.example.showtracker

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.showtracker.model.Episode
import com.example.showtracker.model.TVShow
import com.example.showtracker.model.TVShowShort
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainViewModel(private val dataStoreHelper: DataStoreHelper):ViewModel() {
    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.Home)

    private val _tvShowListState = MutableLiveData<List<TVShowShort>>()
    val topRated: LiveData<List<TVShowShort>> = _tvShowListState
    private val _tvShowRecommendationsListState = MutableLiveData<List<TVShowShort>>()
    val recommendations: LiveData<List<TVShowShort>> = _tvShowRecommendationsListState

    private val _tvShowSearchState = mutableStateOf(TVShowListState())
    val tvShowSearchState: State<TVShowListState> = _tvShowSearchState

    private val _tvShowState = mutableStateOf(TVShowState())

    private val _loadedTVShows = MutableLiveData<List<TVShow>>()
    val loadedTVShows: MutableLiveData<List<TVShow>> = _loadedTVShows

    private var currentRecommendationPage = 1
    private var currentTopRatedPage = 1
    private var lastDisplayedShow = -1

    val currentScreen: MutableState<Screen>
        get() = _currentScreen

    fun setCurrentScreen(screen: Screen) {
        _currentScreen.value = screen
    }

    fun saveTVShowsToDataStore(tvShow: TVShow) {
        val showsToSave = _loadedTVShows.value.orEmpty().toMutableList()
        val index = showsToSave.indexOfFirst { it.id == tvShow.id }

        if (index != -1) showsToSave[index] = tvShow
        else showsToSave.add(tvShow)

        viewModelScope.launch {
            val success = dataStoreHelper.saveShows(showsToSave.toList()).first()
            if (success) {
                Log.d("MainViewModel", "Save shows success")
            } else {
                // Error saving show
            }
        }
    }

    fun loadTVShowsFromDataStore() {
        viewModelScope.launch {
            dataStoreHelper.loadShows().collect { shows ->
                _loadedTVShows.value = shows
            }
        }
    }

    fun loadTVShowById(id: Int): TVShow {
        var show = _loadedTVShows.value.orEmpty().find { it.id == id }

        if(show == null) {
            fetchTVShow(id)
            show = _tvShowState.value.show
        }

        return show
    }

    fun loadWatchlist(): List<TVShowShort> {
        return _loadedTVShows.value.orEmpty().filter { it.watchlist }.map { show ->
            TVShowShort(
                id = show.id,
                name = show.name,
                poster_path = show.poster_path,
                backdrop_path = show.backdrop_path,
                vote_average = show.vote_average,
                vote_count = show.vote_count
            )
        }
    }

    fun fetchTVShowTopRated(isFirstLoad: Boolean = true) {
        viewModelScope.launch {
            try {
                lastDisplayedShow = -1
                if (isFirstLoad) currentTopRatedPage = 1

                Log.d("MainViewModel", "Fetching TV shows...")
                val response = apiService.getTopRatedTVShows(page = currentTopRatedPage)
                Log.d("MainViewModel", "Response: $response")
                if (currentTopRatedPage == 1) {
                    _tvShowListState.value = response.results.sortedByDescending { it.vote_count }

                } else {
                    val currentList = _tvShowListState.value.orEmpty().toMutableList()
                    response.results.sortedByDescending { it.vote_count }.forEach { result ->
                        if(currentList.none { it.id == result.id }) currentList.add(result)
                    }
                    _tvShowListState.value = currentList
                }
                currentTopRatedPage++

            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching TV shows: ${e.message}", e)
            }
        }
    }

    fun fetchTVShowRecommendationsList(id: Int) {
        Log.d("MainViewModel", "Id-> $id")
        if (id != -1) {
            viewModelScope.launch {
                try {
                    if (lastDisplayedShow != id) currentRecommendationPage = 1

                    Log.d("MainViewModel", "Fetching recommendations shows... with id $id")
                    val response = apiService.getRecommendations(id, currentRecommendationPage)
                    Log.d("MainViewModel", "Response: $response")
                    if (currentRecommendationPage == 1) {
                        _tvShowRecommendationsListState.value = response.results

                    } else {
                        val currentList = _tvShowRecommendationsListState.value.orEmpty().toMutableList()
                        response.results.forEach { result ->
                            if(currentList.none { it.id == result.id }) currentList.add(result)
                        }
                        _tvShowRecommendationsListState.value = currentList
                    }
                    currentRecommendationPage++
                    lastDisplayedShow = id

                } catch (e: Exception) {
                    Log.e("MainViewModel", "Error fetching TV shows: ${e.message}", e)
                }
            }
        }
    }

    fun fetchTVShowSearch(query: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getTVShowsBySearch(query)
                _tvShowSearchState.value = _tvShowSearchState.value.copy(
                    list = response.results.sortedByDescending { it.vote_count },
                    error = null
                )

            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching search TV shows: ${e.message}", e)
                _tvShowSearchState.value = _tvShowSearchState.value.copy(
                    error = "Error fetching TV Shows ${e.message}"
                )
            }
        }
    }

    private fun fetchTVShow(id: Int) {
        if (id != -1) {
            viewModelScope.launch {
                try {
                    val response = apiService.getTVShowById(id)

                    // Send Specials season to end of list
                    val seasons = response.seasons.toMutableList()
                    seasons.removeAll { it.season_number == 0 }
                    val seasonSpecials = response.seasons.find { it.season_number == 0 }
                    if (seasonSpecials != null) seasons.add(seasonSpecials)


                    val episodesList = mutableListOf<Episode>()
                    for(seasonNumber in 1..response.number_of_seasons) {
                        episodesList.addAll(apiService.getEpisodesBySeason(id, seasonNumber).episodes)
                    }

                    // Get and send Specials episodes to end of list
                    if (response.seasons.find { it.season_number == 0 } != null) {
                        episodesList.addAll(apiService.getEpisodesBySeason(id, 0).episodes)
                    }

                    val show = response.copy(seasons = seasons, episodes = episodesList)
                    _tvShowState.value = _tvShowState.value.copy(
                        show = show
                    )

                    Log.d("MainViewModel", "Fetch tv show by id success")

                } catch (e: Exception) {
                    Log.e("MainViewModel", "Error fetching TV Show with id $id: ${e.message}", e)
                    _tvShowState.value = _tvShowState.value.copy(
                        error = "Error fetching TV Show with id $id ${e.message}"
                    )
                }
            }
        }
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        val result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
    }

    fun setMockTVShowLists(mockTVShows: List<TVShowShort>) {
        //_tvShowListState.value = TVShowListState(list = mockTVShows)
        _tvShowSearchState.value = TVShowListState(list = mockTVShows)
    }

    fun setMockTVShow(show: TVShow) {
        _tvShowState.value = TVShowState(show = show)
    }

    fun setMockLoadedTVShows(mockTVShows: List<TVShow>) {
        _loadedTVShows.value = mockTVShows
    }

    data class TVShowListState(
        val list: List<TVShowShort> = emptyList(),
        val error: String? = null
    )

    data class TVShowState(
        val show: TVShow = TVShow(-1, "", "", -1, -1, "", "", null, null, -1f, -1, emptyList(), emptyList(), emptyList(), false, null),
        val error: String? = null
    )
}