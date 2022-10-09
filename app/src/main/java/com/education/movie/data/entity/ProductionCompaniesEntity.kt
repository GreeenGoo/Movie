package com.education.movie.data.entity

import com.google.gson.annotations.SerializedName

data class ProductionCompaniesEntity(
    val id: Int,

    @SerializedName("logo_path")
    val logoPath: String,

    val name: String,

    @SerializedName("origin_country")
    val originCountry: String,
)