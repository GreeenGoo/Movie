package com.education.movie.data.models.movie

import com.google.gson.annotations.SerializedName

data class SpokenLanguagesResponse(
    @SerializedName("english_name")
    val englishName: String,

    @SerializedName("iso_639_1")
    val initials: String,

    @SerializedName("name")
    val name: String
    )