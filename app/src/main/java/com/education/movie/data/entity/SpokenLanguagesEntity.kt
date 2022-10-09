package com.education.movie.data.entity

import com.google.gson.annotations.SerializedName

data class SpokenLanguagesEntity(

    @SerializedName("english_name")
    val englishName: String,

    @SerializedName("iso_639_1")
    val shortName: String,

    @SerializedName("name")
    val fullName: String
    )