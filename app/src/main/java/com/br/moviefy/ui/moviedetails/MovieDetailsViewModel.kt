package com.br.moviefy.ui.moviedetails


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.moviefy.data.model.MovieDetails

import com.br.moviefy.data.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel (private val repository: MovieRepository) : ViewModel() {

    val mMoviesDetailsLiveData = MutableLiveData<MovieDetails>()
    val loadingDetailsLiveData = MutableLiveData<Boolean>()
    val errorMovieDetailsLiveData = MutableLiveData<Boolean>()


    fun getMovie(id : Int){
        loadingDetailsLiveData.value = true
        errorMovieDetailsLiveData.value = false


        viewModelScope.launch {
            try {

                this@MovieDetailsViewModel.mMoviesDetailsLiveData.value = repository.getMovieDetails(id)

            } catch (e : Exception){
                errorMovieDetailsLiveData.value = true
            }
            loadingDetailsLiveData.value = false

        }
    }


    fun getShareText(): String {
        var shareText = "${mMoviesDetailsLiveData.value?.title}\n\n"
        shareText += mMoviesDetailsLiveData.value?.overview
        return shareText
    }


}