package com.example.showtracker

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.showtracker.model.TVShow
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {

    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.Home)

    private val _tvShowState = mutableStateOf(TVShowState())
    val tvShowState: State<TVShowState> = _tvShowState

    init {
        fetchTVShows()
    }

    val currentScreen: MutableState<Screen>
        get() = _currentScreen

    fun setCurrentScreen(screen: Screen) {
        _currentScreen.value = screen
    }

    private fun fetchTVShows() {
        viewModelScope.launch {
            try {
                val response = tvShowService.getTVShows()
                _tvShowState.value = _tvShowState.value.copy(
                    list = response.tvShows,
                    loading = false,
                    error = null
                )

            } catch (e: Exception) {
                _tvShowState.value = _tvShowState.value.copy(
                    loading = false,
                    error = "Error fetching TV Shows ${e.message}"
                )
            }
        }
    }

    data class TVShowState(
        val loading: Boolean = true,
        val list: List<TVShow> = emptyList(),
        val error: String? = null
    )
}