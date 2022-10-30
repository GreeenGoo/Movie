package com.education.movie.data.network

import com.education.movie.data.models.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("3/movie/popular")
    suspend fun getFirstPageOfPopularMovies(): Response<MovieResponse>
}
