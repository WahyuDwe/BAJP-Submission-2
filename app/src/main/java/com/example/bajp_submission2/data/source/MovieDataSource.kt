package com.example.bajp_submission2.data.source

import androidx.lifecycle.LiveData
import com.example.bajp_submission2.data.source.local.ContentEntity

interface MovieDataSource {
    fun getMovies(): LiveData<List<ContentEntity>>
    fun getDetailMovies(): LiveData<ContentEntity>
    fun getTvShow(): LiveData<List<ContentEntity>>
    fun getDetailTvShow(): LiveData<ContentEntity>
}