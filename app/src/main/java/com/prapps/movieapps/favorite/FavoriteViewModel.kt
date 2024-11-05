package com.prapps.movieapps.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.prapps.core.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val favoriteMovies = movieUseCase.getFavoriteMovies().asLiveData()
}
