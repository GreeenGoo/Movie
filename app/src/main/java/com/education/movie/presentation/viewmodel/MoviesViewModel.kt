package com.education.movie.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.education.movie.data.entity.PageOfMoviesEntity
import com.education.movie.data.repository.MoviesRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    val myResponse: MutableLiveData<Response<PageOfMoviesEntity>> = MutableLiveData()

    fun getPageOfMovies(){
        viewModelScope.launch {
            val response = repository.getPageOfMovies()
            myResponse.value = response
        }
    }
}