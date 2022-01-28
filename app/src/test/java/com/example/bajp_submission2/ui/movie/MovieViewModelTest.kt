package com.example.bajp_submission2.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.bajp_submission2.data.source.MovieDataRepository
import com.example.bajp_submission2.data.source.local.DetailEntity
import com.example.bajp_submission2.data.source.local.MovieEntity
import com.example.bajp_submission2.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieDataRepository: MovieDataRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieDataRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovie = DataDummy.dataDummyMovies()
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovie

        `when`(movieDataRepository.getMovies()).thenReturn(movies)
        val movieEntities = viewModel.getMovies().value
        verify(movieDataRepository).getMovies()
        assertNotNull(movieEntities)
        assertEquals(3, movieEntities?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)

    }
}