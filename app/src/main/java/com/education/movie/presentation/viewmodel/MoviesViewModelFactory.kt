package com.education.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.education.movie.data.repository.MoviesRepository

class MoviesViewModelFactory(private val repository: MoviesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesViewModel(repository) as T
    }
}