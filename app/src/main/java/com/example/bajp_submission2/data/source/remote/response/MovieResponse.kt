package com.example.bajp_submission2.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("results")
    val results: List<DetailMovieResponse>
)

