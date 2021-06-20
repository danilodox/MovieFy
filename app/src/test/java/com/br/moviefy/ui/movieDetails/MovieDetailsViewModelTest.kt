package com.br.moviefy.ui.movieDetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.br.moviefy.data.FakeMovieDetailsData
import com.br.moviefy.data.model.MovieDetails
import com.br.moviefy.data.repository.MovieRepository
import com.br.moviefy.ui.moviedetails.MovieDetailsViewModel
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class MovieDetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val repository = mockk <MovieRepository>()
    private val mMoviesDetailsLiveDataObserver = mockk<Observer<MovieDetails>>(relaxed = true)
    private val loadingDetailsLiveDataObserver = mockk<Observer<Boolean>>(relaxed = true)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }


    @Test
    fun `call movie details with success then set moviesLiveData and show loading`() {

        val viewModel = instantiateViewModel()
        coEvery { repository.getMovieDetails(FakeMovieDetailsData.MOVIE_DETAIL.id!!) } throws Exception()

        viewModel.getMovie(FakeMovieDetailsData.MOVIE_DETAIL.id!!)

        coVerifyOrder {
            loadingDetailsLiveDataObserver.onChanged(FakeMovieDetailsData.IS_LOADING)
            repository.getMovieDetails(FakeMovieDetailsData.MOVIE_DETAIL.id!!)
            loadingDetailsLiveDataObserver.onChanged(FakeMovieDetailsData.NOT_LOADING)
        }
        confirmVerified(loadingDetailsLiveDataObserver)
        confirmVerified(repository)
        confirmVerified(loadingDetailsLiveDataObserver)


    }



    private fun instantiateViewModel(): MovieDetailsViewModel {
        val viewModel =
            MovieDetailsViewModel(repository)
        viewModel.mMoviesDetailsLiveData.observeForever(mMoviesDetailsLiveDataObserver)
        viewModel.loadingDetailsLiveData.observeForever(loadingDetailsLiveDataObserver)
        return viewModel
    }
}