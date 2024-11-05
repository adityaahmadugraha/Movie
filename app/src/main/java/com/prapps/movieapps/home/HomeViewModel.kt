package com.prapps.movieapps.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.prapps.core.core.domain.usecase.MovieUseCase

class HomeViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    suspend fun getMovie(apiKey: String) =
        movieUseCase.getAllMovie(apiKey).asLiveData()
}