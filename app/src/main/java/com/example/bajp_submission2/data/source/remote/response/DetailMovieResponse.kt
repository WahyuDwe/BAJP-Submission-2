package com.example.bajp_submission2.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("original_title")
    val originalTitle: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    // Title For Tv Show
    @field:SerializedName("original_name")
    val originalName: String,

    // Get Genre
    @field:SerializedName("genres")
    val genres: List<DetailGenre>
)
