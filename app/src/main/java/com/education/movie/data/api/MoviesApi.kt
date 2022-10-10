package com.education.movie.data.api

import com.education.movie.data.entity.PageOfMoviesEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface MoviesApi {

    @GET("3/movie/popular?api_key=cbf6b34df260fad6fb3b7a41ef4b067c&language=en-US&page=1")
    fun getFirstPageOfPopularMovies(): Single<PageOfMoviesEntity>
}