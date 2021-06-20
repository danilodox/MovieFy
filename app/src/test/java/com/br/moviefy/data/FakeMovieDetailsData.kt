package com.br.moviefy.data

import com.br.moviefy.data.model.Genre
import com.br.moviefy.data.model.MovieDetails

object FakeMovieDetailsData {

    const val IS_LOADING = true
    const val NOT_LOADING = false


    val LIST_GENRE = Genre(
        1,
        "ala"
    )

    val MOVIE_DETAIL = MovieDetails(
        false,
         listOf(LIST_GENRE),
        "a",
        1,
        "",
        "",
        "asdas sad asd asd asd ",
        5.0,
        "",
        "10/23/2040",
        "EEE AA"



    )

    val MOVIE_LIST = listOf(
        MOVIE_DETAIL,
        MOVIE_DETAIL,
        MOVIE_DETAIL
    )
}