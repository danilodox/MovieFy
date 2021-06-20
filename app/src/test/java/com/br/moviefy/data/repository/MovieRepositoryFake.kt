package com.br.moviefy.data.repository

import com.br.moviefy.data.FakeMovieDetailsData
import com.br.moviefy.data.network.RetrofitService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class MovieRepositoryFake {
    private val testDispatcher = TestCoroutineDispatcher()
    private val apiService : RetrofitService = mockk()

    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUp(){
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `call Movie details From Service`() = runBlockingTest{
        coEvery { apiService.service.getMovieDetails(FakeMovieDetailsData.MOVIE_DETAIL.id!!) } returns FakeMovieDetailsData.MOVIE_DETAIL
        MovieRepositoryImpl ( apiService ).getMovieDetails(FakeMovieDetailsData.MOVIE_DETAIL.id!!)
        coVerify { apiService.service.getMovieDetails(FakeMovieDetailsData.MOVIE_DETAIL.id!!) }
    }
}