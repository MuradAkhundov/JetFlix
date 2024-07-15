package com.muradakhundov.jetflix.main.di

import com.muradakhundov.jetflix.common.util.Constants
import com.muradakhundov.jetflix.main.api.MovieService
import com.muradakhundov.jetflix.main.data.repo.MovieRepository
import com.muradakhundov.jetflix.main.data.source.remote.MovieRemoteDataSource
import com.muradakhundov.jetflix.main.domain.usecase.GetPopularMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL).build()

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)


    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(movieService: MovieService) : MovieRemoteDataSource = MovieRemoteDataSource(movieService)

    @Provides
    @Singleton
    fun provideMovieRemoteRepository(movieRemoteDataSource: MovieRemoteDataSource) : MovieRepository = MovieRepository(movieRemoteDataSource)

}


@Module
@InstallIn(SingletonComponent::class)
object MovieUseCaseModule{

    @Provides
    @Singleton
    fun provideGetPopularMoviesUseCase(repository: MovieRepository) : GetPopularMoviesUseCase = GetPopularMoviesUseCase(repository)

}




