package com.br.moviefy.ui.movies


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.moviefy.data.model.MoviesResponse
import com.br.moviefy.data.repository.MovieRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {


    val mMoviesLiveData : MutableLiveData<MoviesResponse> = MutableLiveData()
    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val errorMoviesLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private lateinit var popularMovies : MoviesResponse

    private var searchJob: Job? = null
    private val mSearchTextLiveData : MutableLiveData<String> = MutableLiveData()
    private val mSearchMovieLiveData : MutableLiveData<MoviesResponse> = MutableLiveData()


    fun getMovies(){
        loadingLiveData.value = true
        errorMoviesLiveData.value = false

        viewModelScope.launch {

            try {

                mMoviesLiveData.value = repository.getUpcomingMovies()
                //mMoviesLiveData.value = repository.getPopularMovies()
                popularMovies = mMoviesLiveData.value!!

            } catch (e : Exception){
                errorMoviesLiveData.value = true
            }
            loadingLiveData.value = false

        }


    }


    fun onSearchQuery(query : String){

        searchJob = viewModelScope.launch {
            delay(400)
            if (query.length > 2) {
                mSearchTextLiveData.value = query
                getMovieByQuery(query)
            }else if(query.isEmpty()){
                mMoviesLiveData.value = popularMovies
            }
        }

    }

    private fun getMovieByQuery(query: String){
        viewModelScope.launch {

            mSearchMovieLiveData.value = repository.getMovieByQuery(query)
            mMoviesLiveData.value = mSearchMovieLiveData.value
        }
    }


}