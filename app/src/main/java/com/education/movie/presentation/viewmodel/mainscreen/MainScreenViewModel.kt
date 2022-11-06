package com.education.movie.presentation.viewmodel.mainscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.education.movie.data.models.mainscreen.MovieResponse
import com.education.movie.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.Response

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: MoviesRepository,
) : ViewModel() {
    val listOfMovies = MutableLiveData<Response<MovieResponse>>()
    init {
        viewModelScope.launch {
            try {
                listOfMovies.value = repository.getPageOfMovies()
            } catch (e: NullPointerException) {
            }
        }
    }

    companion object{
        const val BUNDLE_KEY = "currentMovie"
    }
}