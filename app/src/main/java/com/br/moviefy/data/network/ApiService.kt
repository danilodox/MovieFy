package com.br.moviefy.data.network


import com.br.moviefy.data.model.MovieDetails
import com.br.moviefy.data.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): MoviesResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(): MoviesResponse

    @GET("search/movie")
    suspend fun getMovieByQuery(@Query("query") query: String): MoviesResponse

    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") id: Int): MovieDetails
}