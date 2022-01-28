package com.example.bajp_submission2.network

import com.example.bajp_submission2.BuildConfig
import com.example.bajp_submission2.data.source.remote.response.DetailContentResponse
import com.example.bajp_submission2.data.source.remote.response.ContentResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<ContentResponse>

    @GET("movie/{id}")
    fun getDetailMovie(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<DetailContentResponse>

    @GET("tv/popular")
    fun getTvShow(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<ContentResponse>

    @GET("tv/{id}")
    fun getDetailTvShow(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<DetailContentResponse>
}