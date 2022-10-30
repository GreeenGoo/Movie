package com.education.movie.data.models

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val page: Int,

    val results: List<ListOfMoviesResponse>,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("total_results")
    val totalResults: Int
)