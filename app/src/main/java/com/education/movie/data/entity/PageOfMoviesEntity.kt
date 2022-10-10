package com.education.movie.data.entity

import com.google.gson.annotations.SerializedName

data class PageOfMoviesEntity(
    val page: Int,

    val results: List<MovieListResultEntity>,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("total_results")
    val totalResults: Int
)