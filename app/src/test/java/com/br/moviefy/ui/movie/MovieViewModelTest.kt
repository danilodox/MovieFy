package com.br.moviefy.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.br.moviefy.data.FakeMovieData
import com.br.moviefy.data.model.MovieDetails
import com.br.moviefy.data.model.MoviesResponse
import com.br.moviefy.data.repository.MovieRepository
import com.br.moviefy.ui.movies.MovieViewModel
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

class MovieViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val repository = mockk <MovieRepository>()
    private val mMoviesLiveDataObserver = mockk<Observer<MoviesResponse>>(relaxed = true)
    private val loadingLiveDataObserver = mockk<Observer<Boolean>>(relaxed = true)

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
    fun `call movies with success then set moviesLiveData and show loading`() {

        val viewModel = instantiateViewModel()
        coEvery { repository.getUpcomingMovies() } throws Exception()

        viewModel.getMovies()

        coVerifyOrder {
            loadingLiveDataObserver.onChanged(FakeMovieData.IS_LOADING)
            repository.getUpcomingMovies()
            loadingLiveDataObserver.onChanged(FakeMovieData.NOT_LOADING)
        }
        confirmVerified(loadingLiveDataObserver)
        confirmVerified(repository)
        confirmVerified(loadingLiveDataObserver)


    }





    private fun instantiateViewModel(): MovieViewModel {
        val viewModel =
            MovieViewModel(repository)
        viewModel.mMoviesLiveData.observeForever(mMoviesLiveDataObserver)
        viewModel.loadingLiveData.observeForever(loadingLiveDataObserver)
        return viewModel
    }

}