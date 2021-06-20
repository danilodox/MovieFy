package com.br.moviefy.data

import com.br.moviefy.data.model.Genre
import com.br.moviefy.data.model.Movie
import com.br.moviefy.data.model.MovieDetails
import com.br.moviefy.data.model.MoviesResponse

object FakeMovieData {

    const val IS_LOADING = true
    const val NOT_LOADING = false



    val MOVIES = Movie(
        false,
        "a",
        listOf(27,12),
        550,
        "EN",
        "AAAA",
        "adasdsadasds sadasd",
        144.2,
        "a",
        "12/03/2031",
        "AAAA",
        false,
        45f,
        1
    )

    val MOVIE_RESPONSE = MoviesResponse(
        3,
        listOf(MOVIES),
        1,
        1
        )


}