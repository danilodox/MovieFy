package com.br.moviefy.di


import com.br.moviefy.ui.moviedetails.MovieDetailsViewModel
import com.br.moviefy.ui.movies.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { MovieViewModel( get()) }
    viewModel { MovieDetailsViewModel( get()) }

}