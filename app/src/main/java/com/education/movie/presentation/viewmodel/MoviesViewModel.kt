package com.education.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.education.movie.data.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel() : ViewModel(){

    private val repository = MoviesRepository()

    fun getFightClubMovie(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFightClubMovie()
        }
    }

}