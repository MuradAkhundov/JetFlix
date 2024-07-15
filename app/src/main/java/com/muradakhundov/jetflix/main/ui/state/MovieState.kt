package com.muradakhundov.jetflix.main.ui.state

import com.muradakhundov.jetflix.main.data.model.movies.MovieResponse

data class MovieState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val popularMovies : MovieResponse? = null,
    val upcomingMovies : MovieResponse? = null,
    val topRatedMovies : MovieResponse? = null
)
