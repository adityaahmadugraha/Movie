package com.prapps.core.core.domain.usecase

import com.prapps.core.core.domain.model.MovieFavorite
import com.prapps.core.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override suspend fun getAllMovie(key: String) = movieRepository.getAllMovie(key)


    override fun getDetailMovie(
        movieId: Int,
        language: String,
        key: String
    ) = movieRepository.getDetailMovie(movieId, language, key).also {}

    override suspend fun addMovieToFavorites(movie: MovieFavorite) {
        movieRepository.addMovieToFavorites(movie)
    }

    override suspend fun removeMovieFromFavorites(movie: MovieFavorite) {
        movieRepository.removeMovieFromFavorites(movie)
    }

    override fun getFavoriteMovies(): Flow<List<MovieFavorite>> {
        return movieRepository.getFavoriteMovies()
    }

}