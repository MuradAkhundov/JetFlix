package com.muradakhundov.jetflix.main.data.repo

import com.muradakhundov.jetflix.main.data.model.movies.MovieResponse
import com.muradakhundov.jetflix.main.data.source.remote.MovieRemoteDataSource
import com.muradakhundov.jetflix.main.domain.query.MovieQuery
import retrofit2.Call
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieDataSource : MovieRemoteDataSource) {

    suspend fun getPopularMovies(query : MovieQuery.GetMovieQuery): Call<MovieResponse> = movieDataSource.getPopularMovies(query)
}