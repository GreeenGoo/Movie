package com.education.movie.data.repository

import com.education.movie.data.entity.PageOfMoviesEntity
import com.education.movie.data.network.MoviesClient
import retrofit2.Response

class MoviesRepository {
    suspend fun getPageOfMovies(): Response<PageOfMoviesEntity> {
        return MoviesClient.api.getFirstPageOfPopularMovies()
    }
}