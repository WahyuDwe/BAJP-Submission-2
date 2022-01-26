package com.example.bajp_submission2.data.source.local

import com.google.gson.annotations.SerializedName

data class ContentEntity(
    @field:SerializedName("id")
    var id: Int,

    @field:SerializedName("original_title")
    var title: String,

    @field:SerializedName("genres")
    var genre: List<String>,

    @field:SerializedName("overview")
    var description: String,

    @field:SerializedName("release_date")
    var date: String,

    @field:SerializedName("vote_average")
    var score: Double,

    @field:SerializedName("poster_path")
    var imagePath: String
)


