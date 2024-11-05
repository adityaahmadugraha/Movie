package com.prapps.core.core.domain.repository

import com.prapps.core.core.data.Resource
import com.prapps.core.core.domain.model.MovieDetail
import com.prapps.core.core.domain.model.MovieFavorite
import com.prapps.core.core.domain.model.MovieList
import kotlinx.coroutines.flow.Flow


interface IMovieRepository {

    suspend fun getAllMovie(apiKey: String): Flow<Resource<List<MovieList>>>

    fun getDetailMovie(
        movieId: Int,
        language: String,
        key: String
    ): Flow<Resource<MovieDetail>>

    suspend fun addMovieToFavorites(movie: MovieFavorite)


    suspend fun removeMovieFromFavorites(movie: MovieFavorite)

    fun getFavoriteMovies(): Flow<List<MovieFavorite>>


}

