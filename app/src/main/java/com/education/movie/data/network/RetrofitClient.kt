package com.education.movie.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val api_key = "cbf6b34df260fad6fb3b7a41ef4b067c"
    private const val BASE_URL = "https://api.themoviedb.org"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val url = chain
                            .request()
                            .url
                            .newBuilder()
                            .addQueryParameter("api_key", api_key)
                            .build()
                        chain.proceed(chain.request().newBuilder().url(url).build())
                    }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: RetrofitApi by lazy {
        retrofit.create(RetrofitApi::class.java)
    }
}