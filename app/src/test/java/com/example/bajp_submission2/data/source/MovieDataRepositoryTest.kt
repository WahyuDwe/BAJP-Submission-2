package com.example.bajp_submission2.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bajp_submission2.data.source.remote.RemoteDataSource
import com.example.bajp_submission2.utils.DataDummy
import com.example.bajp_submission2.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MovieDataRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieDataRepository = FakeMovieDataRepository(remote)

    private val movieResponse = DataDummy.dataDummyRemoteMovie()
    private val movieId = movieResponse[0].id.toString()
    private val movieDetail = DataDummy.dataDummyRemoteDetailMovie()

    private val tvShowResponse = DataDummy.dataDummyRemoteTvShow()
    private val tvShowId = tvShowResponse[0].id.toString()
    private val tvShowDetail = DataDummy.dataDummyRemoteDetailTvShow()

    @Test
    fun getMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponse)
            null
        }.`when`(remote).getMovies(any())
        val movieEntities = LiveDataTestUtil.getValue(movieDataRepository.getMovies())
        verify(remote).getMovies(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponse.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getDetailMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailMoviesCallback)
                .onDetailMovieReceived(movieDetail)
            null
        }.`when`(remote).getDetailMovie(any(), eq(movieId))
        val detailMovieEntities =
            LiveDataTestUtil.getValue(movieDataRepository.getDetailMovies(movieId))
        verify(remote).getDetailMovie(any(), eq(movieId))
        assertNotNull(detailMovieEntities)
        assertEquals(movieDetail.id, detailMovieEntities.id)
    }

    @Test
    fun getTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback)
                .onAllTvShowReceived(tvShowResponse)
            null
        }.`when`(remote).getTvShow(any())
        val tvShowEntities = LiveDataTestUtil.getValue(movieDataRepository.getTvShow())
        verify(remote).getTvShow(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun getDetailTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadDetailTvShowCallback)
                .onDetailTvShowReceived(tvShowDetail)
        }.`when`(remote).getDetailTvShow(any(), eq(tvShowId))
        val detailTvShowEntities =
            LiveDataTestUtil.getValue(movieDataRepository.getDetailTvShow(tvShowId))
        verify(remote).getDetailTvShow(any(), eq(tvShowId))
        assertNotNull(detailTvShowEntities)
        assertEquals(tvShowDetail.id, detailTvShowEntities.id)
    }
}