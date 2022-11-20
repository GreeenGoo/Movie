package com.education.movie.presentation.viewmodel.popularmovies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.education.movie.data.models.movie.MovieResponse
import com.education.movie.data.models.popularmovies.ListOfMoviesResponse
import com.education.movie.data.models.popularmovies.PageOfMoviesResponse
import com.education.movie.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.Response

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val repository: MoviesRepository,
) : ViewModel() {
    val pageOfMovies = MutableLiveData<Response<PageOfMoviesResponse>>()
    val movie = MutableLiveData<Response<MovieResponse>>()
    var listOfDownloadedMovies: ArrayList<ListOfMoviesResponse> = ArrayList()
    var numberOfPage = 1

    init {
        showPageOfMovies(numberOfPage)
    }

    fun showPageOfMovies(page: Int){
        viewModelScope.launch {
            try {
                pageOfMovies.value = repository.getPageOfMovies(page)
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