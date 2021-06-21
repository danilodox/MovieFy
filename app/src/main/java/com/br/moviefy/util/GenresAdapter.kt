package com.br.moviefy.util

import com.br.moviefy.data.model.Genre


class GenresAdapter {

    fun getGenresByInt(listGenres : List<Int>) : String{
        var genreInString = ""
        val mapGenres = mapOf(
            28 to "Ação",
            12 to "Aventura",
            16 to "Animação",
            35 to "Comédia",
            80 to "Crime",
            99 to "Documentário",
            18 to "Drama",
            10751 to "Família",
            14 to "Fantasia",
            36 to "História",
            27 to "Terror",
            10402 to "Música",
            9648 to "Mistério",
            10749 to "Romance",
            878 to "Ficção ciêntífica",
            10770 to "Cinema TV",
            53 to "Thriller",
            10752 to "Guerra",
            37 to "Faroeste"
        )

        listGenres.forEach {
            if (mapGenres.containsKey(it)){
                genreInString += mapGenres[it] + ", "
            }
        }



        genreInString = genreInString.dropLast(2)

        return genreInString

    }

    fun switchGenresIntForString(listGenres : List<Genre>) : String{
        var genreInString = ""

        listGenres.forEach {
            genreInString += it.name + ", "
        }

        genreInString = genreInString.dropLast(2)

        return genreInString
    }
}