package com.prapps.movieapps.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prapps.core.core.data.Resource
import com.prapps.core.core.domain.model.MovieDetail
import com.prapps.core.core.domain.model.MovieFavorite
import com.prapps.core.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getDetailMovie(
        movieId: Int,
        language: String,
        apiKey: String
    ): Flow<Resource<MovieDetail>> {
        return movieUseCase.getDetailMovie(movieId, language, apiKey)
    }

    fun addMovieToFavorites(movie: MovieFavorite) {
        viewModelScope.launch {
            movieUseCase.addMovieToFavorites(movie)
        }
    }

    fun removeMovieFromFavorites(movie: MovieFavorite) {
        viewModelScope.launch {
            movieUseCase.removeMovieFromFavorites(movie)
        }
    }

    fun getFavoriteMovies() = movieUseCase.getFavoriteMovies()
}


