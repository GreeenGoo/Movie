package com.education.movie.presentation.fragments.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.education.movie.R
import com.education.movie.presentation.viewmodel.mainscreen.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception
import kotlinx.android.synthetic.main.fragment_movie.title_and_release_date
import kotlinx.android.synthetic.main.main_screen_recyclerview_item.title

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val viewModel: MainScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundleNote : Int? = arguments?.getInt(MainScreenViewModel.BUNDLE_KEY)
        bundleNote?.let {
            viewModel.getMovie(it)
            viewModel.movie.observe(viewLifecycleOwner, Observer { response ->
                try {
                   title_and_release_date.text = response.body()?.title
                } catch (e : Exception){
                    Log.e("Movie Mistake", "There is a mistake with downloading the movie.")
                }
            })
        }
    }
}