package com.example.bajp_submission2.data.source.remote


import android.content.ContentValues.TAG
import android.util.Log
import com.example.bajp_submission2.data.source.remote.response.DetailMovieResponse
import com.example.bajp_submission2.data.source.remote.response.MovieResponse
import com.example.bajp_submission2.network.ApiConfig
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

    fun getMovies(callback: LoadMovesCallback) {
        val client = ApiConfig.getService().getMovies()
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.results?.let { callback.onAllMoviesReceived(it) }
                Log.d(TAG, "onResponse : ${response.body()}")
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("Failure", t.message!!)
            }
        })
    }

    fun getDetailMovie(callback: LoadDetailMoviesCallback, movieId: String) {
        val client = ApiConfig.getService().getDetailMovie(movieId)
        client.enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: Response<DetailMovieResponse>
            ) {
                response.body()?.let { callback.onDetailMovieReceived(it) }
                Log.d(TAG, "onResponse : ${response.body()}")
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.d("Failure", t.message!!)
            }
        })
    }

    fun getTvShow(callback: LoadTvShowCallback) {
        val client = ApiConfig.getService().getTvShow()
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.results?.let { callback.onAllTvShowReceived(it) }
                Log.d(TAG, "onResponse : ${response.body()}")
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("Failure", t.message!!)
            }

        })
    }

    fun getDetailTvShow(callback: LoadDetailTvShowCallback, tvShowId: String) {
        val client = ApiConfig.getService().getDetailTvShow(tvShowId)
        client.enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: Response<DetailMovieResponse>
            ) {
                response.body()?.let { callback.onDetailTvShowReceived(it) }
                Log.d(TAG, "onResponse : ${response.body()}")
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.d("Failure", t.message!!)
            }
        })
    }


    interface LoadMovesCallback {
        fun onAllMoviesReceived(movies: List<DetailMovieResponse>)
    }

    interface LoadDetailMoviesCallback {
        fun onDetailMovieReceived(detailMovie: DetailMovieResponse)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(tvShow: List<DetailMovieResponse>)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailTvShowReceived(tvShow: DetailMovieResponse)
    }
}