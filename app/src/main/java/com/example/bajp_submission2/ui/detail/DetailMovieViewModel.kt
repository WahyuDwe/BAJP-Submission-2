package com.example.bajp_submission2.ui.detail

import androidx.lifecycle.ViewModel
import com.example.bajp_submission2.data.ContentEntity
import com.example.bajp_submission2.utils.DataDummy

class DetailMovieViewModel : ViewModel() {
    companion object {
        const val MOVIE = "movie"
        const val TV_SHOW = "tv_show"
    }

    private lateinit var content: ContentEntity

    fun setContent(id: String, category: String) {
        when (category) {
            TV_SHOW -> {
                for (tvShows in DataDummy.dataDummyTvShow()) {
                    if (tvShows.id == id) content = tvShows
                }
            }

            MOVIE -> {
                for (movies in DataDummy.dataDummyMovies()) {
                    if (movies.id == id) content = movies
                }
            }
        }
    }

    fun getContentDetail() = content
}