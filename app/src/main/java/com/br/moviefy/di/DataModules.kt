package com.br.moviefy.di

import com.br.moviefy.data.network.interceptor.AuthInterceptor
import com.br.moviefy.data.network.RetrofitService
import com.br.moviefy.data.repository.MovieRepository
import com.br.moviefy.data.repository.MovieRepositoryImpl
import org.koin.dsl.module

val dataModule = module {

    single { AuthInterceptor() }

    factory { RetrofitService() }

    factory <MovieRepository> { MovieRepositoryImpl (get()) }




}