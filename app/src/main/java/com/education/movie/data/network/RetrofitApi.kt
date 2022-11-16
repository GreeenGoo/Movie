package com.education.movie.data.network

import com.education.movie.data.models.popularmovies.PageOfMoviesResponse
import com.education.movie.data.models.movie.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApi {

    @GET("3/movie/popular")
    suspend fun getFirstPageOfPopularMovies(@Query("page") page: Int): Response<PageOfMoviesResponse>

    @GET("3/movie/{movieId}")
    suspend fun getMovie(
        @Path("movieId") movieId: Int
    ): Response<MovieResponse>
}
