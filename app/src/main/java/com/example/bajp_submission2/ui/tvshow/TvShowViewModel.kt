package com.example.bajp_submission2.ui.tvshow

import androidx.lifecycle.ViewModel
import com.example.bajp_submission2.utils.DataDummy

class TvShowViewModel : ViewModel() {
    fun getTvShow() = DataDummy.dataDummyTvShow()
}