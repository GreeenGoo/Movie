package com.education.movie.presentation.viewmodel.mainscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.education.movie.data.models.mainscreen.PageOfMoviesResponse
import com.education.movie.data.models.movie.MovieResponse
import com.education.movie.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.Response

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val repository: MoviesRepository,
) : ViewModel() {
    val listOfMovies = MutableLiveData<Response<PageOfMoviesResponse>>()
    val movie = MutableLiveData<Response<MovieResponse>>()

    init {
        viewModelScope.launch {
            try {
                listOfMovies.value = repository.getPageOfMovies()
            } catch (e: Exception) {
            }
        }
    }

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            try {
               movie.value = repository.getMovie(movieId)
            } catch (e: Exception){
            }
        }
    }

    companion object {
        const val BUNDLE_KEY = "currentMovie"
    }
}