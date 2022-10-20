package com.education.movie.data.network

import com.education.movie.data.entity.PageOfMoviesEntity
import retrofit2.Response
import retrofit2.http.GET

interface MoviesApi {

    @GET("3/movie/popular?api_key=cbf6b34df260fad6fb3b7a41ef4b067c&language=en-US&page=1")
    suspend fun getFirstPageOfPopularMovies(): Response<PageOfMoviesEntity>
}
