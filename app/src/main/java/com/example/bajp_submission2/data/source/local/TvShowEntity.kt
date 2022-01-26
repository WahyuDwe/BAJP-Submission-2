package com.example.bajp_submission2.data.source.local

import com.example.bajp_submission2.data.source.remote.response.DetailGenre
import com.google.gson.annotations.SerializedName

data class TvShowEntity(
    @field:SerializedName("id")
    var id: Int,

    @field:SerializedName("original_name")
    var title: String,

    @field:SerializedName("genres")
    var genre: List<DetailGenre>,

    @field:SerializedName("overview")
    var description: String,

    @field:SerializedName("release_date")
    var date: String,

    @field:SerializedName("vote_average")
    var score: Double,

    @field:SerializedName("poster_path")
    var imagePath: String
)
