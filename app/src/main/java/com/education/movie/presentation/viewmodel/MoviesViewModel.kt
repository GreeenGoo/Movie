package com.education.movie.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.education.movie.data.models.MovieResponse
import com.education.movie.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.Response

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {
    val myResponse = MutableLiveData<Response<MovieResponse>>()

    fun getPageOfMovies() {
        viewModelScope.launch {
            myResponse.value = repository.getPageOfMovies()
        }
    }
}