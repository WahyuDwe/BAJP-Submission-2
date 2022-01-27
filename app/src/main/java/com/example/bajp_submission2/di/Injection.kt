package com.example.bajp_submission2.di

import android.content.Context
import com.example.bajp_submission2.data.source.MovieDataRepository
import com.example.bajp_submission2.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): MovieDataRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MovieDataRepository.getInstance(remoteDataSource)
    }
}