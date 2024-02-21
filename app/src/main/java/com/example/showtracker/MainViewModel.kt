package com.example.showtracker

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.showtracker.model.DiscoverShow
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.Home)

    private val _tvShowState = mutableStateOf(DiscoverShowState())
    val tvShowState: State<DiscoverShowState> = _tvShowState

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
                Log.d("MainViewModel", "Fetching TV shows...")
                val response = apiService.getTVShows()
                Log.d("MainViewModel", "Response: $response")
                _tvShowState.value = _tvShowState.value.copy(
                    list = response.results,
                    loading = false,
                    error = null
                )

            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching TV shows: ${e.message}", e)
                _tvShowState.value = _tvShowState.value.copy(
                    loading = false,
                    error = "Error fetching TV Shows ${e.message}"
                )
            }
        }
    }

    data class DiscoverShowState(
        val loading: Boolean = true,
        val list: List<DiscoverShow> = emptyList(),
        val error: String? = null
    )
}