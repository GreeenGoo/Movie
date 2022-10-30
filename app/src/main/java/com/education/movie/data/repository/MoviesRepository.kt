package com.education.movie.data.repository

import com.education.movie.data.models.MovieResponse
import com.education.movie.data.network.RetrofitClient
import retrofit2.Response

class MoviesRepository(private val retrofitClient : RetrofitClient) {
    suspend fun getPageOfMovies(): Response<MovieResponse> {
        return retrofitClient.api.getFirstPageOfPopularMovies()
    }
}