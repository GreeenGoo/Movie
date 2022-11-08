package com.education.movie.data.network

import com.education.movie.data.models.mainscreen.PageOfMoviesResponse
import com.education.movie.data.models.movie.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApi {

    @GET("3/movie/popular")
    suspend fun getFirstPageOfPopularMovies(): Response<PageOfMoviesResponse>

    @GET("3/movie/{movieId}")
    suspend fun getMovie(
        @Path("movieId") movieId: Int
    ): Response<MovieResponse>
}
