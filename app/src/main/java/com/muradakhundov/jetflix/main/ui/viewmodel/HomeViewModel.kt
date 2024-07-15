package com.muradakhundov.jetflix.main.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muradakhundov.jetflix.common.util.Constants
import com.muradakhundov.jetflix.main.domain.query.MovieQuery
import com.muradakhundov.jetflix.main.domain.usecase.GetPopularMoviesUseCase
import com.muradakhundov.jetflix.main.domain.usecase.GetTopRatedMoviesUseCase
import com.muradakhundov.jetflix.main.domain.usecase.GetUpComingMoviesUseCase
import com.muradakhundov.jetflix.main.ui.state.MovieState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val popularMoviesUseCase: GetPopularMoviesUseCase,
    val upComingMoviesUseCase: GetUpComingMoviesUseCase,
    val topRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieState(isLoading = false))
    val uiState: StateFlow<MovieState> = _uiState.asStateFlow()

    init {
        loadPopularMovies()
        loadTopRatedMovies()
        loadUpcomingMovies()
    }


    private fun loadPopularMovies() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = popularMoviesUseCase(MovieQuery.GetMovieQuery(Constants.API_KEY, 1))
            if (result.isSuccessful) {
                _uiState.update { it.copy(popularMovies = result.body(), isLoading = false) }
            } else {
                _uiState.update { it.copy(error = result.errorBody()?.string(), isLoading = false) }
            }
        }
    }

    private fun loadTopRatedMovies() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = topRatedMoviesUseCase(MovieQuery.GetMovieQuery(Constants.API_KEY, 1))
            if (result.isSuccessful) {
                _uiState.update { it.copy(topRatedMovies = result.body(), isLoading = false) }
            } else {
                _uiState.update { it.copy(error = result.errorBody()?.string(), isLoading = false) }
            }
        }
    }

    private fun loadUpcomingMovies() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = upComingMoviesUseCase(MovieQuery.GetMovieQuery(Constants.API_KEY, 1))
            if (result.isSuccessful) {
                _uiState.update { it.copy(upcomingMovies = result.body(), isLoading = false) }
            } else {
                _uiState.update { it.copy(error = result.errorBody()?.string(), isLoading = false) }
            }
        }
    }

    var searchText by mutableStateOf("")
        private set

    var isSearching by mutableStateOf(false)
        private set

    fun onSearchTextChange(newText: String) {
        searchText = newText
    }

    fun onToggleSearch() {
        isSearching = !isSearching
    }
}