package com.education.movie.data.entity

import com.google.gson.annotations.SerializedName

data class MoviesEntity(
    @SerializedName("adult")
    val isAdult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("belongs_to_collection")
    val belongsToCollection: String,

    val budget: Int,

    val genres: List<GenresEntity>,

    @SerializedName("homepage")
    val homePage: String,

    val id: Int,

    @SerializedName("imdb_id")
    val imdbId: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_title")
    val originalTitle: String,

    val overview: String,

    val popularity: Float,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesEntity>,

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountriesEntity>,

    @SerializedName("release_date")
    val releaseDate : String,

    val revenue: Int,

    val runtime: Int,

    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguagesEntity>,

    val status: String,

    val tagline: String,

    val title: String,

    val video: Boolean,

    @SerializedName("vote_average")
    val voteAverage: Float,

    @SerializedName("vote_count")
    val voteCount: Int,
    )