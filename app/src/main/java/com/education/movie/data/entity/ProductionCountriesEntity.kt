package com.education.movie.data.entity

import com.google.gson.annotations.SerializedName

data class ProductionCountriesEntity(
    @SerializedName("iso_3166_1")
    val shortCountryName: String,

    @SerializedName("name")
    val fullCountryName: String
)