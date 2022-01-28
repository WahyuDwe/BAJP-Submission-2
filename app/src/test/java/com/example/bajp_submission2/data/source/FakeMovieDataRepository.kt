package com.example.bajp_submission2.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bajp_submission2.data.source.local.DetailEntity
import com.example.bajp_submission2.data.source.local.MovieEntity
import com.example.bajp_submission2.data.source.local.TvShowEntity
import com.example.bajp_submission2.data.source.remote.RemoteDataSource
import com.example.bajp_submission2.data.source.remote.response.DetailContentResponse

class FakeMovieDataRepository(private val remoteDataSource: RemoteDataSource) : MovieDataSource {
    override fun getMovies(): LiveData<List<MovieEntity>> {
        val moviesResult = MutableLiveData<List<MovieEntity>>()

        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(contents: List<DetailContentResponse>) {
                val listMovie = ArrayList<MovieEntity>()
                for (response in contents) {
                    with(response) {
                        val movieEntity = MovieEntity(
                            id = id,
                            title = originalTitle,
                            description = overview,
                            date = releaseDate,
                            score = voteAverage,
                            imagePath = posterPath
                        )
                        listMovie.add(movieEntity)
                    }
                }
                moviesResult.postValue(listMovie)
            }
        })
        return moviesResult
    }

    override fun getDetailMovies(movieId: String): LiveData<DetailEntity> {
        val detailMovieResult = MutableLiveData<DetailEntity>()

        remoteDataSource.getDetailMovie(object : RemoteDataSource.LoadDetailMoviesCallback {
            override fun onDetailMovieReceived(detailMovie: DetailContentResponse) {
                with(detailMovie) {
                    val genreList = ArrayList<String>()

                    for (genre in genres) {
                        genreList.add(genre.name)
                    }

                    val movieDetail = DetailEntity(
                        id = id,
                        title = originalTitle,
                        genre = genreList,
                        description = overview,
                        date = releaseDate,
                        score = voteAverage,
                        imagePath = posterPath
                    )
                    detailMovieResult.postValue(movieDetail)
                }
            }

        }, movieId)
        return detailMovieResult
    }

    override fun getTvShow(): LiveData<List<TvShowEntity>> {
        val tvShowResult = MutableLiveData<List<TvShowEntity>>()

        remoteDataSource.getTvShow(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvShow: List<DetailContentResponse>) {
                val listTv = ArrayList<TvShowEntity>()
                for (response in tvShow) {
                    with(response) {
                        val tvShowEntity = TvShowEntity(
                            id = id,
                            name = originalName,
                            overview = overview,
                            date = FirstAirDate,
                            score = voteAverage,
                            imagePath = posterPath
                        )
                        listTv.add(tvShowEntity)
                    }
                }
                tvShowResult.postValue(listTv)
            }

        })
        return tvShowResult
    }

    override fun getDetailTvShow(tvShowId: String): LiveData<DetailEntity> {
        val detailTvShowResult = MutableLiveData<DetailEntity>()

        remoteDataSource.getDetailTvShow(object : RemoteDataSource.LoadDetailTvShowCallback {
            override fun onDetailTvShowReceived(DetailTvShow: DetailContentResponse) {
                with(DetailTvShow) {
                    val genreList = ArrayList<String>()

                    for (genre in genres) {
                        genreList.add(genre.name)
                    }

                    val detailTvShow = DetailEntity(
                        id = id,
                        genre = genreList,
                        title = originalName,
                        description = overview,
                        date = FirstAirDate,
                        score = voteAverage,
                        imagePath = posterPath
                    )
                    detailTvShowResult.postValue(detailTvShow)
                }
            }

        }, tvShowId)
        return detailTvShowResult
    }
}