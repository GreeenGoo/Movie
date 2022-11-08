package com.education.movie.data.repository

import com.education.movie.data.models.mainscreen.PageOfMoviesResponse
import com.education.movie.data.models.movie.MovieResponse
import com.education.movie.data.network.RetrofitClient
import retrofit2.Response

class MoviesRepository(private val retrofitClient : RetrofitClient) {
    suspend fun getPageOfMovies(): Response<PageOfMoviesResponse>? {
        return try {
            retrofitClient.api.getFirstPageOfPopularMovies()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getMovie(movieId : Int): Response<MovieResponse>?{
        return try {
            retrofitClient.api.getMovie(movieId)
        } catch (e: Exception){
            null
        }
    }
}