package com.example.bajp_submission2.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.bajp_submission2.data.source.MovieDataRepository
import com.example.bajp_submission2.data.source.local.DetailEntity
import com.example.bajp_submission2.ui.detail.DetailMovieViewModel.Companion.MOVIE
import com.example.bajp_submission2.ui.detail.DetailMovieViewModel.Companion.TV_SHOW
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
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val dataDummyDetailMovies = DataDummy.dataDummyDetailMovies()
    private val movieId = dataDummyDetailMovies.id.toString()

    private val dataDummyDetailTvShow = DataDummy.dataDummyDetailTvShow()
    private val tvShowId = dataDummyDetailTvShow.id.toString()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieDataRepository: MovieDataRepository

    @Mock
    private lateinit var detailObserver: Observer<DetailEntity>

    // Testing for detail Movie
    @Before
    fun setUpMovies() {
        viewModel = DetailMovieViewModel(movieDataRepository)
    }

    @Test
    fun getContentDetailMovie() {
        val detailMovie = MutableLiveData<DetailEntity>()
        detailMovie.value = dataDummyDetailMovies

        `when`(movieDataRepository.getDetailMovies(movieId)).thenReturn(detailMovie)
        viewModel.setContent(movieId, MOVIE)
        val movieEntity = viewModel.getContentDetail().value
        verify(movieDataRepository).getDetailMovies(movieId)
        assertNotNull(movieEntity)
        assertEquals(dataDummyDetailMovies.id, movieEntity?.id)
        assertEquals(dataDummyDetailMovies.title, movieEntity?.title)
        assertEquals(dataDummyDetailMovies.genre, movieEntity?.genre)
        assertEquals(dataDummyDetailMovies.date, movieEntity?.date)
        assertEquals(dataDummyDetailMovies.score, movieEntity?.score)
        assertEquals(dataDummyDetailMovies.description, movieEntity?.description)
        assertEquals(dataDummyDetailMovies.imagePath, movieEntity?.imagePath)

        viewModel.getContentDetail().observeForever(detailObserver)
        verify(detailObserver).onChanged(dataDummyDetailMovies)
    }

    // Testing for detail Tv Show
    @Before
    fun setUpTvShow() {
        viewModel = DetailMovieViewModel(movieDataRepository)
    }

    @Test
    fun getContentDetailTvShow() {
        val detailTvShow = MutableLiveData<DetailEntity>()
        detailTvShow.value = dataDummyDetailTvShow

        `when`(movieDataRepository.getDetailTvShow(tvShowId)).thenReturn(detailTvShow)
        viewModel.setContent(tvShowId, TV_SHOW)
        val tvShowEntity = viewModel.getContentDetail().value
        assertNotNull(tvShowEntity)
        assertEquals(dataDummyDetailTvShow.id, tvShowEntity?.id)
        assertEquals(dataDummyDetailTvShow.title, tvShowEntity?.title)
        assertEquals(dataDummyDetailTvShow.genre, tvShowEntity?.genre)
        assertEquals(dataDummyDetailTvShow.date, tvShowEntity?.date)
        assertEquals(dataDummyDetailTvShow.score, tvShowEntity?.score)
        assertEquals(dataDummyDetailTvShow.description, tvShowEntity?.description)
        assertEquals(dataDummyDetailTvShow.imagePath, tvShowEntity?.imagePath)

        viewModel.getContentDetail().observeForever(detailObserver)
        verify(detailObserver).onChanged(dataDummyDetailTvShow)
    }
}