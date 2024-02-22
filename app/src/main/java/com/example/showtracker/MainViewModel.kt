package com.example.showtracker

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.showtracker.model.TVShowShort
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.Home)

    private val _tvShowListState = mutableStateOf(TVShowListState())
    val tvShowListState: State<TVShowListState> = _tvShowListState

    init {
        fetchTVShowList()
    }

    val currentScreen: MutableState<Screen>
        get() = _currentScreen

    fun setCurrentScreen(screen: Screen) {
        _currentScreen.value = screen
    }

    private fun fetchTVShowList() {
        viewModelScope.launch {
            try {
                Log.d("MainViewModel", "Fetching TV shows...")
                val response = apiService.getTopRatedTVShows()
                Log.d("MainViewModel", "Response: $response")
                _tvShowListState.value = _tvShowListState.value.copy(
                    list = response.results,
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

    data class TVShowListState(
        val list: List<TVShowShort> = emptyList(),
        val error: String? = null
    )
}