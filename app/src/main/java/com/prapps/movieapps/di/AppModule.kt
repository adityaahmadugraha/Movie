package com.prapps.movieapps.di

import com.prapps.core.core.domain.usecase.MovieInteractor
import com.prapps.core.core.domain.usecase.MovieUseCase
import com.prapps.movieapps.detail.DetailViewModel
import com.prapps.movieapps.favorite.FavoriteViewModel
import com.prapps.movieapps.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}