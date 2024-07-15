package com.muradakhundov.jetflix.main.api

import com.muradakhundov.jetflix.main.data.model.movies.MovieResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey : String,
        @Query("page") page : Int) : Response<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpComingMovie(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int): Response<MovieResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int): Response<MovieResponse>
}