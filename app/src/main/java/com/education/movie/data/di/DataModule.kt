package com.education.movie.data.di

import com.education.movie.data.network.RetrofitClient
import com.education.movie.data.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideGetRetrofitClient() : RetrofitClient{
        return RetrofitClient
    }

    @Provides
    @Singleton
    fun provideGetMoviesRepository(retrofitClient: RetrofitClient) : MoviesRepository{
        return MoviesRepository(retrofitClient)
    }

}