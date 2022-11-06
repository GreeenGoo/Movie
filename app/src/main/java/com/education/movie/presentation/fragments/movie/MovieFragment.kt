package com.education.movie.presentation.fragments.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.education.movie.R
import com.education.movie.presentation.viewmodel.mainscreen.MainScreenViewModel
import kotlinx.android.synthetic.main.fragment_movie.text_view

class MovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundleNote = arguments?.getInt(MainScreenViewModel.BUNDLE_KEY)
        text_view.text = bundleNote.toString()
    }
}