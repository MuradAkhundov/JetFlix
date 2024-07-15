package com.muradakhundov.jetflix.main.domain.usecase

import com.muradakhundov.jetflix.main.data.model.movies.MovieResponse
import com.muradakhundov.jetflix.main.data.repo.MovieRepository
import com.muradakhundov.jetflix.main.domain.query.MovieQuery
import retrofit2.Response
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(query : MovieQuery.GetMovieQuery) : Response<MovieResponse> {
        return movieRepository.getTopRatedMovies(query)
    }
}