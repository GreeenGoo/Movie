package com.education.movie.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.education.movie.R
import com.education.movie.application.MoviesApp
import com.education.movie.data.api.MoviesApi
import com.education.movie.presentation.viewmodel.MoviesViewModel


class MoviesFragment : Fragment() {

    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesViewModelInit()
    }

    private fun moviesViewModelInit() {
        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        moviesViewModel.getFightClubMovie((activity?.application as? MoviesApp)?.moviesApi)
    }
}