package com.education.movie.data.api

import com.education.movie.data.entity.MoviesEntity
import io.reactivex.Single
import retrofit2.http.GET

interface MoviesApi {

    @GET("./movies/fight-club")
    fun getFightClubMovie(): Single<MoviesEntity>
}