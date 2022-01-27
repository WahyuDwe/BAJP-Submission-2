package com.example.bajp_submission2.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    // Title and Date For Tv Show
    @SerializedName("original_name")
    val originalName: String,

    @SerializedName("first_air_date")
    var date: String,

    // Get Genre
    @SerializedName("genres")
    val genres: List<DetailGenre>
)
