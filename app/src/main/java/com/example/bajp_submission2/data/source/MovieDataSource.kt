package com.example.bajp_submission2.data.source

import androidx.lifecycle.LiveData
import com.example.bajp_submission2.data.source.local.DetailEntity
import com.example.bajp_submission2.data.source.local.MovieEntity
import com.example.bajp_submission2.data.source.local.TvShowEntity

interface MovieDataSource {
    fun getMovies(): LiveData<List<MovieEntity>>
    fun getDetailMovies(movieId: String): LiveData<DetailEntity>
    fun getTvShow(): LiveData<List<TvShowEntity>>
    fun getDetailTvShow(tvShowId: String): LiveData<DetailEntity>
}