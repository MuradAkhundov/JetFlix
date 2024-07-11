package com.muradakhundov.jetflix.main.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muradakhundov.jetflix.authentication.ui.state.AuthState
import com.muradakhundov.jetflix.common.util.Constants
import com.muradakhundov.jetflix.main.domain.query.MovieQuery
import com.muradakhundov.jetflix.main.domain.usecase.GetPopularMoviesUseCase
import com.muradakhundov.jetflix.main.ui.state.MovieState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Callback
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    val popularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieState(isLoading = false))
    val uiState : StateFlow<MovieState> = _uiState.asStateFlow()


    init {
        loadPopularMovies()
        loadOngoingMovies()
        //3rd function
    }

    private fun loadPopularMovies(){
        viewModelScope.launch {
            _uiState.emit(MovieState(isLoading = true))


          val result = popularMoviesUseCase(MovieQuery.GetMovieQuery(Constants.apiKey,1))
            _uiState.emit(MovieState(isLoading = true, success = true))
            //Callback
        }

    }

    private fun loadOngoingMovies(){
        // some code
    }


    private fun loadIncomingMovies(){

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