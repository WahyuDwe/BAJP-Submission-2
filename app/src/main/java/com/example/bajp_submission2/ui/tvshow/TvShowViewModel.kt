package com.example.bajp_submission2.ui.tvshow

import androidx.lifecycle.ViewModel
import com.example.bajp_submission2.data.source.MovieDataRepository

class TvShowViewModel(private val movieDataRepository: MovieDataRepository) : ViewModel() {
    fun getTvShow() = movieDataRepository.getTvShow()
}