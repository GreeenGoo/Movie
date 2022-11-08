package com.education.movie.data.models.movie

import com.google.gson.annotations.SerializedName

data class ProductionCountriesResponse(
    @SerializedName("iso_3166_1")
    val iso_3166_1: String,

    @SerializedName("name")
    val name: String
)