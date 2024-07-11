package com.muradakhundov.jetflix.main.domain.usecase

import com.muradakhundov.jetflix.main.data.model.movies.MovieResponse
import com.muradakhundov.jetflix.main.data.repo.MovieRepository
import com.muradakhundov.jetflix.main.domain.query.MovieQuery
import retrofit2.Call
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
   suspend operator fun invoke(query : MovieQuery.GetMovieQuery) : Call<MovieResponse> {
       return movieRepository.getPopularMovies(query)
   }
}