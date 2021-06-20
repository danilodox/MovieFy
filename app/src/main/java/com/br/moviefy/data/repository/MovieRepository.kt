package com.br.moviefy.data.repository

import com.br.moviefy.data.model.MovieDetails
import com.br.moviefy.data.model.MoviesResponse

interface MovieRepository {

    suspend fun getUpcomingMovies(): MoviesResponse?
    suspend fun getPopularMovies(): MoviesResponse?
    suspend fun getMovieByQuery(query : String) : MoviesResponse?
    suspend fun getMovieDetails(id : Int) : MovieDetails?
}