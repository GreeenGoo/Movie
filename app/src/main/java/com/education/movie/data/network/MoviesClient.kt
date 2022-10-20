package com.education.movie.data.network

import com.education.movie.data.utils.Utils.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesClient {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: MoviesApi by lazy {
        retrofit.create(MoviesApi::class.java)
    }
}