package com.muradakhundov.jetflix.main.data.source.remote

import com.muradakhundov.jetflix.main.api.MovieService
import com.muradakhundov.jetflix.main.data.model.movies.MovieResponse
import com.muradakhundov.jetflix.main.domain.query.MovieQuery
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteDataSource
@Inject constructor(private val movieService: MovieService) {

     suspend fun getPopularMovies(query : MovieQuery.GetMovieQuery): Response<MovieResponse> =
        movieService.getPopularMovie(apiKey = query.apiKey,page = query.page)

    suspend fun getTopRatedMovies(query : MovieQuery.GetMovieQuery) : Response<MovieResponse> =
        movieService.getTopRatedMovie(apiKey = query.apiKey,page = query.page)

    suspend fun getUpcomingMovies(query : MovieQuery.GetMovieQuery) : Response<MovieResponse> =
        movieService.getUpComingMovie(apiKey = query.apiKey , page = query.page)

}