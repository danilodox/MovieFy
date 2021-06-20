package com.br.moviefy.data.repository



import com.br.moviefy.data.model.MovieDetails
import com.br.moviefy.data.model.MoviesResponse
import com.br.moviefy.data.network.RetrofitService


class MovieRepositoryImpl(private val apiService: RetrofitService): MovieRepository{
    override suspend fun getUpcomingMovies(): MoviesResponse? {
        return try {
            apiService.service.getUpcomingMovies()
        } catch (e : Exception) { null }
    }


    override suspend fun getPopularMovies(): MoviesResponse? {

        return try {
            apiService.service.getPopularMovies()
        } catch (e : Exception) { null }

    }

    override suspend fun getMovieByQuery(query: String): MoviesResponse? {
        return try {
            apiService.service.getMovieByQuery(query)
        } catch (e : Exception) { null }
    }



    override suspend fun getMovieDetails(id: Int): MovieDetails? {
        return try {
            apiService.service.getMovieDetails(id)
        } catch (e : Exception) { null }

    }
}