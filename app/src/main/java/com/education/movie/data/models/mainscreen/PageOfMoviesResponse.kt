package com.education.movie.data.models.mainscreen

import com.google.gson.annotations.SerializedName

data class PageOfMoviesResponse(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<ListOfMoviesResponse>,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("total_results")
    val totalResults: Int
)