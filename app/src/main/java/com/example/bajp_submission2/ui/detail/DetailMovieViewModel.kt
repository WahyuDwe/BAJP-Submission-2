package com.example.bajp_submission2.ui.detail

import androidx.lifecycle.ViewModel
import com.example.bajp_submission2.data.source.local.ContentEntity
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

            }

            MOVIE -> {

            }
        }
    }

    fun getContentDetail() = content
}