package com.example.bajp_submission2.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.bajp_submission2.data.source.MovieDataRepository
import com.example.bajp_submission2.data.source.local.DetailEntity


class DetailMovieViewModel(private val movieDataRepository: MovieDataRepository) : ViewModel() {
    companion object {
        const val MOVIE = "movie"
        const val TV_SHOW = "tv_show"
    }

    private lateinit var content: LiveData<DetailEntity>

    fun setContent(id: String, category: String) {
        when (category) {
            TV_SHOW -> {
                content = movieDataRepository.getDetailTvShow(id)
            }

            MOVIE -> {
                content = movieDataRepository.getDetailMovies(id)
            }
        }
    }

    fun getContentDetail() = content
}