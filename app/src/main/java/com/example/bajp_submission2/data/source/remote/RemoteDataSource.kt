package com.example.bajp_submission2.data.source.remote

import android.content.ContentValues.TAG
import android.util.Log
import com.example.bajp_submission2.data.source.remote.response.ContentResponse
import com.example.bajp_submission2.data.source.remote.response.DetailContentResponse
import com.example.bajp_submission2.network.ApiConfig
import com.example.bajp_submission2.utils.EspressoIdlingResources
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RemoteDataSource {

    companion object {
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource()
        }
    }

    fun getMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResources.increment()
        val client = ApiConfig.getService().getMovies()
        client.enqueue(object : Callback<ContentResponse> {
            override fun onResponse(
                call: Call<ContentResponse>,
                response: Response<ContentResponse>
            ) {
                response.body()?.results?.let { callback.onAllMoviesReceived(it) }
                Log.d(TAG, "onResponse : ${response.body()}")
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<ContentResponse>, t: Throwable) {
                Log.d("Failure", t.message!!)
                EspressoIdlingResources.decrement()
            }
        })
    }

    fun getDetailMovie(callback: LoadDetailMoviesCallback, movieId: String) {
        EspressoIdlingResources.increment()
        val client = ApiConfig.getService().getDetailMovie(movieId)
        client.enqueue(object : Callback<DetailContentResponse> {
            override fun onResponse(
                call: Call<DetailContentResponse>,
                response: Response<DetailContentResponse>
            ) {
                response.body()?.let { callback.onDetailMovieReceived(it) }
                Log.d(TAG, "onResponse : ${response.body()}")
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<DetailContentResponse>, t: Throwable) {
                Log.d("Failure", t.message!!)
                EspressoIdlingResources.decrement()
            }
        })
    }

    fun getTvShow(callback: LoadTvShowCallback) {
        EspressoIdlingResources.increment()
        val client = ApiConfig.getService().getTvShow()
        client.enqueue(object : Callback<ContentResponse> {
            override fun onResponse(
                call: Call<ContentResponse>,
                response: Response<ContentResponse>
            ) {
                response.body()?.results?.let { callback.onAllTvShowReceived(it) }
                Log.d(TAG, "onResponse : ${response.body()}")
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<ContentResponse>, t: Throwable) {
                Log.d("Failure", t.message!!)
                EspressoIdlingResources.decrement()
            }

        })
    }

    fun getDetailTvShow(callback: LoadDetailTvShowCallback, tvShowId: String) {
        EspressoIdlingResources.increment()
        val client = ApiConfig.getService().getDetailTvShow(tvShowId)
        client.enqueue(object : Callback<DetailContentResponse> {
            override fun onResponse(
                call: Call<DetailContentResponse>,
                response: Response<DetailContentResponse>
            ) {
                response.body()?.let { callback.onDetailTvShowReceived(it) }
                Log.d(TAG, "onResponse : ${response.body()}")
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<DetailContentResponse>, t: Throwable) {
                Log.d("Failure", t.message!!)
                EspressoIdlingResources.decrement()
            }
        })
    }


    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movie: List<DetailContentResponse>)
    }

    interface LoadDetailMoviesCallback {
        fun onDetailMovieReceived(detailMovie: DetailContentResponse)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(tvShow: List<DetailContentResponse>)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailTvShowReceived(DetailTvShow: DetailContentResponse)
    }
}