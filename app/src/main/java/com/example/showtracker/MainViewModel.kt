package com.example.showtracker

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

    private val _tvShowListState = mutableStateOf(TVShowListState())
    val tvShowListState: State<TVShowListState> = _tvShowListState

    private val _tvShowSearchState = mutableStateOf(TVShowListState())
    val tvShowSearchState: State<TVShowListState> = _tvShowSearchState

    private val _tvShowState = mutableStateOf(TVShowState())
    val tvShowState: State<TVShowState> = _tvShowState

    private val _loadedTVShows = MutableLiveData<List<TVShow>>()

    init {
        fetchTVShowList()
        loadTVShowsFromDataStore()
    }

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

    private fun loadTVShowsFromDataStore() {
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

    private fun fetchTVShowList() {
        viewModelScope.launch {
            try {
                Log.d("MainViewModel", "Fetching TV shows...")
                val response = apiService.getTopRatedTVShows()
                Log.d("MainViewModel", "Response: $response")
                _tvShowListState.value = _tvShowListState.value.copy(
                    list = response.results.sortedByDescending { it.vote_count },
                    error = null
                )

            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching TV shows: ${e.message}", e)
                _tvShowListState.value = _tvShowListState.value.copy(
                    error = "Error fetching TV Shows ${e.message}"
                )
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

    fun fetchTVShow(id: Int) {
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

    fun setMockTVShowLists(mockTVShows: List<TVShowShort>) {
        _tvShowListState.value = TVShowListState(list = mockTVShows)
        _tvShowSearchState.value = TVShowListState(list = mockTVShows)
    }

    fun setMockTVShow(show: TVShow) {
        _tvShowState.value = TVShowState(show = show)
    }

    data class TVShowListState(
        val list: List<TVShowShort> = emptyList(),
        val error: String? = null
    )

    data class TVShowState(
        val show: TVShow = TVShow(-1, "", "", -1, -1, "", "", null, null, -1f, -1, emptyList(), emptyList(), emptyList()),
        val error: String? = null
    )
}