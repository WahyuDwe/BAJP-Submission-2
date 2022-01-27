package com.example.bajp_submission2.ui.detail

import com.example.bajp_submission2.utils.DataDummy
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dataMovies = DataDummy.dataDummyMovies()[0]
    private val dataTvShow = DataDummy.dataDummyTvShow()[0]
    private val dummyMovieId = dataMovies.id
    private val dummyTvShowId = dataTvShow.id

    // Testing for data Movie
    @Before
    fun setUpMovies() {
        viewModel = DetailMovieViewModel()
    }

    @Test
    fun getContentDetailMovie() {
        viewModel.setContent(dummyMovieId, "movie")
        val movieEntity = viewModel.getContentDetail()
        assertNotNull(movieEntity)
        assertEquals(dataMovies.id, movieEntity.id)
        assertEquals(dataMovies.title, movieEntity.title)
        assertEquals(dataMovies.genre, movieEntity.genre)
        assertEquals(dataMovies.date, movieEntity.date)
        assertEquals(dataMovies.score, movieEntity.score)
        assertEquals(dataMovies.description, movieEntity.description)
        assertEquals(dataMovies.imagePath, movieEntity.imagePath)
    }

    // Testing for data Tv Show
    @Before
    fun setUpTvShow() {
        viewModel = DetailMovieViewModel()
    }

    @Test
    fun getContentDetailTvShow() {
        viewModel.setContent(dummyTvShowId, "tv_show")
        val tvShowEntity = viewModel.getContentDetail()
        assertNotNull(tvShowEntity)
        assertEquals(dataTvShow.id, tvShowEntity.id)
        assertEquals(dataTvShow.title, tvShowEntity.title)
        assertEquals(dataTvShow.genre, tvShowEntity.genre)
        assertEquals(dataTvShow.date, tvShowEntity.date)
        assertEquals(dataTvShow.score, tvShowEntity.score)
        assertEquals(dataTvShow.overview, tvShowEntity.description)
        assertEquals(dataTvShow.imagePath, tvShowEntity.imagePath)
    }
}