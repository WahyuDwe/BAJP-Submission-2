package com.example.bajp_submission2.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailGenre(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String
)
