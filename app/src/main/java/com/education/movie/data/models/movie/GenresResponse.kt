package com.education.movie.data.models.movie

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String
)