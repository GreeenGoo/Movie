package com.education.movie.data.repository

import com.education.movie.data.models.popularmovies.PageOfMoviesResponse
import com.education.movie.data.models.movie.MovieResponse
import com.education.movie.data.network.RetrofitClient
import retrofit2.Response

class MoviesRepository(private val retrofitClient : RetrofitClient) {
    suspend fun getPageOfMovies(page: Int): Response<PageOfMoviesResponse>? {
        try {
            return retrofitClient.api.getFirstPageOfPopularMovies(page)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    suspend fun getMovie(movieId : Int): Response<MovieResponse>?{
        try {
            return retrofitClient.api.getMovie(movieId)
        } catch (e: Exception){
            e.printStackTrace()
        }
        return null
    }
}