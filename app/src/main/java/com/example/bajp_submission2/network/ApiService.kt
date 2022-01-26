package com.example.bajp_submission2.network

import com.example.bajp_submission2.BuildConfig
import com.example.bajp_submission2.data.source.remote.response.DetailMovieResponse
import com.example.bajp_submission2.data.source.remote.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<MovieResponse>

    @GET("movie/{id}")
    fun getDetailMovie(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<DetailMovieResponse>

    @GET("tv/popular")
    fun getTvShow(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<MovieResponse>

    @GET("tv/{id}")
    fun getDetailTvShow(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<DetailMovieResponse>
}