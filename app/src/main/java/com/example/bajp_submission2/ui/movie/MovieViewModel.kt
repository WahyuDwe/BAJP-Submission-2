package com.example.bajp_submission2.ui.movie

import androidx.lifecycle.ViewModel
import com.example.bajp_submission2.utils.DataDummy

class MovieViewModel : ViewModel() {
    fun getMovies() = DataDummy.dataDummyMovies()
}