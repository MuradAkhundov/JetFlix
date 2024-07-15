package com.muradakhundov.jetflix.main.data.repo

import com.muradakhundov.jetflix.main.data.model.movies.MovieResponse
import com.muradakhundov.jetflix.main.data.source.remote.MovieRemoteDataSource
import com.muradakhundov.jetflix.main.domain.query.MovieQuery
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieDataSource : MovieRemoteDataSource) {
    suspend fun getPopularMovies(query : MovieQuery.GetMovieQuery): Response<MovieResponse> = movieDataSource.getPopularMovies(query)
    suspend fun getTopRatedMovies(query : MovieQuery.GetMovieQuery) : Response<MovieResponse> = movieDataSource.getTopRatedMovies(query)
    suspend fun getUpcomingMovies(query : MovieQuery.GetMovieQuery) : Response<MovieResponse> = movieDataSource.getUpcomingMovies(query)
}