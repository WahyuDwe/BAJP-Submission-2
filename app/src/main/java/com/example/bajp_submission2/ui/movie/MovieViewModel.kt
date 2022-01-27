package com.example.bajp_submission2.ui.movie

import androidx.lifecycle.ViewModel
import com.example.bajp_submission2.data.source.MovieDataRepository

class MovieViewModel(private val movieDataRepository: MovieDataRepository) : ViewModel() {
    fun getMovies() = movieDataRepository.getMovies()
}