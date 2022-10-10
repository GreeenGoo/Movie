package com.education.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.education.movie.data.api.MoviesApi
import com.education.movie.data.repository.MoviesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel() : ViewModel() {

    private val repository = MoviesRepository()
    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun getFightClubMovie(moviesApi: MoviesApi?) {
        moviesApi?.let {
            compositeDisposable.add(
                moviesApi.getFirstPageOfPopularMovies()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({

                    }, {

                    })
            )

            viewModelScope.launch(Dispatchers.IO) {
                repository.getFightClubMovie()
            }
        }

    }

}